package com.ggx.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            for(;;){
                AtomicInteger atomicInteger = new AtomicInteger(0);
                try {
                    Thread.sleep(20000);
                    queue.put(atomicInteger.getAndIncrement());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(()-> {
            for(;;){
                try {
                    Integer task = queue.take();
                    System.out.println("获取到打印任务为：" + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
    }
}
