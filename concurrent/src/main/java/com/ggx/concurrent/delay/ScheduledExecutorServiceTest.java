package com.ggx.concurrent.delay;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("delayTaskThread-%d").build();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(threadFactory);
        for(int i = 0; i < 10; i++){
            DelayThread delayThread = new DelayThread();
            delayThread.setSequenceNumber(i);
            System.out.println("任务-" + i + "加入时间为：" +  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ScheduledFuture<?> scheduledFuture = service.schedule(delayThread, 10, TimeUnit.SECONDS);
            Thread.sleep(1000);
            if(i % 2 == 0){
                scheduledFuture.cancel(true);
            }
        }
    }

    public static class DelayThread extends Thread{
        private int sequenceNumber;

        public int getSequenceNumber(){
            return sequenceNumber;
        }

        public void setSequenceNumber(int sequenceNumber){
            this.sequenceNumber = sequenceNumber;
        }

        @Override
        public void run() {
            System.out.println("任务-" + sequenceNumber + "运行时间为:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
