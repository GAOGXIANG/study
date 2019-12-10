package com.ggx.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ThreadLocal
 */
public class ThreadLocalTest {

    public static void main(String[] args){
        //new ThreadLocal时threadLocalHashCode值已经固定，一个ThreadLocal一个hashCode值
//        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
//        ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
//        for(int i = 0; i < 5; i++){
//            AtomicInteger atomicInteger = new AtomicInteger(1);
//            AtomicLong atomicLong = new AtomicLong(20);
//            Runnable runnable = () -> {
//                integerThreadLocal.set(atomicInteger.intValue());
//                System.out.println(Thread.currentThread().getId() + ":" + atomicInteger.intValue());
//                longThreadLocal.set(atomicLong.longValue());
//                Integer newInteger = atomicInteger.addAndGet(1);
//                integerThreadLocal.set(newInteger);
//                System.out.println(Thread.currentThread().getId() + ":" + integerThreadLocal.get());
//            };
//            runnable.run();
//        }
        CountDownLatchTest test = CountDownLatchTest.getInstance();
    }
}
