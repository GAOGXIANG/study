package com.ggx.threadLocal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 类加载顺序的测试
 */
public class CountDownLatchTest {

    private static CountDownLatchTest instance = new CountDownLatchTest();

    public static CountDownLatchTest getInstance(){
        return instance;
    }

    private CountDownLatchTest() {
        System.out.println("count down latch test init");
        try {
            block();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void block() throws InterruptedException {
        System.out.println("count down latch test block");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable = () -> {
            System.out.println("thread run");
            countDownLatch.countDown();
        };
        countDownLatch.await(10, TimeUnit.SECONDS);
        runnable.run();
        System.out.println("end");
    }
}
