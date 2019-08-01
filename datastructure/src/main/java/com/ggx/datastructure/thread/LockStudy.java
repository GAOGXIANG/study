package com.ggx.datastructure.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁（Fair）：加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得
 * 非公平锁（Nonfair）：加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待
 * 非公平锁性能比公平锁高5~10倍，因为公平锁需要在多核的情况下维护一个队列
 * @author ggx
 * @version 1.0
 * @date 8/31/2017
 */
public class LockStudy {

    ReentrantLock unfairLock = new ReentrantLock(); //不公平锁以为这获取锁的顺序与申请锁的顺序可能不一致
    ReentrantLock fairLock = new ReentrantLock(true);//公平锁先到先得


    public void lockWait(){
        try{
            if(fairLock.tryLock(5, TimeUnit.SECONDS)){

            }
        }catch(Exception e){

        }finally{
            fairLock.unlock();
        }
    }
}
