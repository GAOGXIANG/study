package com.ggx.dispatch;

import java.io.Serializable;

public class Overload {

    public static void sayHello(Object arg){
        System.out.println("hello object");
    }

    public static void sayHello(int arg){
        System.out.println("hello object");
    }

    public static void sayHello(long arg){
        System.out.println("hello long");
    }

    public static void sayHello(Character arg){
        System.out.println("hello character");
    }

    public static void sayHello(char arg){
        System.out.println("hello char");
    }

    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }

    public static void sayHello(Serializable arg){
        System.out.println("hello serializable");
    }

    public static void main(String[] args) {
        //方法匹配优先级:char > int > long > float > double > Character > Serializable > char...
        sayHello('a');
    }
}
