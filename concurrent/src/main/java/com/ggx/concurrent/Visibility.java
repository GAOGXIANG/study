package com.ggx.concurrent;

public class Visibility {

    private boolean flag = false;

    public void refresh(){
        this.flag = true;
        String threadName = Thread.currentThread().getName();
        System.out.println("线程:" + threadName + ":修改线程变量flag");
    }

    public void load(){
        String threadName = Thread.currentThread().getName();
        while(!this.flag){}
        System.out.println("线程："+ threadName + "当前线程嗅探到flag值的变化");
    }

    public static void main(String[] args) {
        Visibility visibility = new Visibility();
        Thread thread = new Thread(() ->{
            visibility.load();
        }) ;
        thread.start();
        Thread refreshThread = new Thread(() ->{
           visibility.refresh();
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshThread.start();
    }
}
