package com.ggx.study.springboot.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class NioReactTest {

    @Test
    public void test() throws Exception {
        NioReactTest test = new NioReactTest();
        test.newGroup();
        test.initAndRegister();
        test.bind();
    }

    abstract class ReactorThread extends Thread{
        private Selector selector;
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        public abstract void handler(SelectableChannel channel) throws IOException, ExecutionException, InterruptedException;

        private ReactorThread() throws IOException {
            selector = Selector.open();
        }

        private volatile boolean running = false;

        @Override
        public void run(){
            while(running){
                try{
                    Runnable task = null;
                    while((task = taskQueue.take()) != null){
                        task.run();
                    }

                    selector.select(1000);
                    //获取查询结果
                    Set<SelectionKey> selected = selector.selectedKeys();
                    //遍历查询结果
                    Iterator<SelectionKey> it = selected.iterator();
                    while(it.hasNext()){
                        SelectionKey selectionKey = it.next();
                        it.remove();
                        int readOps = selectionKey.readyOps();
                        if((readOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 && readOps != 0){
                            try{
                                SelectableChannel channel = (SelectableChannel) selectionKey.attachment();
                                channel.configureBlocking(false);
                                handler(channel);
                                if(!channel.isOpen()){
                                    selectionKey.cancel();
                                }
                            } catch(Exception e){
                                selectionKey.cancel(); // 如果有异常,就取消这个KEY的订阅
                            }
                        }
                    }
                    selector.selectNow();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        private SelectionKey register(SelectableChannel channel) throws ExecutionException, InterruptedException {
            // 为什么register要以任务提交的形式，让reactor线程去处理？
            // 因为线程在执行channel注册到selector的过程中，会和调用selector.select()方法的线程争用同一把锁
            // 而select()方法实在eventLoop中通过while循环调用的，争抢的可能性很高，为了让register能更快的执行，就放到同一个线程来处理
            FutureTask<SelectionKey> task = new FutureTask<>(() -> channel.register(selector, 0, channel));
            taskQueue.add(task);
            return task.get();
        }

        private void doStart(){
            if(!running){
                running = true;
                start();
            }
        }
    }

    public ServerSocketChannel serverSocketChannel;
    // 1、创建多个线程 - accept处理reactor线程 (accept线程)
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];
    // 2、创建多个线程 - io处理reactor线程  (I/O线程)
    private ReactorThread[] subReactorThreads = new ReactorThread[8];

    public void newGroup() throws IOException {
        for(int i = 0; i < subReactorThreads.length; i++){
            subReactorThreads[i] = new ReactorThread() {
                @Override
                public void handler(SelectableChannel channel) throws IOException {
                    SocketChannel ch = (SocketChannel)channel;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while(ch.isOpen() && ch.read(byteBuffer) != 1){
                        if (byteBuffer.position() > 0) break;
                    }
                    if (byteBuffer.position() == 0) return; // 如果没数据了, 则不继续后面的处理
                    byteBuffer.flip();
                    byte[] content = new byte[byteBuffer.limit()];
                    byteBuffer.get(content);

                    // 响应结果 200
                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Length: 11\r\n\r\n" +
                            "Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()) {
                        ch.write(buffer);
                    }
                }
            };
        }
        for(int i = 0; i < mainReactorThreads.length; i++){
            mainReactorThreads[i] = new ReactorThread() {
                AtomicInteger incr = new AtomicInteger(0);
                @Override
                public void handler(SelectableChannel channel) throws IOException, ExecutionException, InterruptedException {
                    ServerSocketChannel ch = (ServerSocketChannel) channel;
                    SocketChannel socketChannel = ch.accept();
                    socketChannel.configureBlocking(false);
                    int index = incr.getAndIncrement() % subReactorThreads.length;
                    ReactorThread workThread = subReactorThreads[index];
                    workThread.doStart();
                    SelectionKey selectionKey = workThread.register(socketChannel);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            };
        }
    }

    private void initAndRegister() throws Exception{
        // 1、 创建ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 2、 将serverSocketChannel注册到selector
        int index = new Random().nextInt(mainReactorThreads.length);
        mainReactorThreads[index].doStart();
        SelectionKey selectionKey = mainReactorThreads[index].register(serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }

    private void bind() throws IOException {
        serverSocketChannel.bind(new InetSocketAddress(8081));
    }
}
