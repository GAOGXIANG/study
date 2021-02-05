package com.ggx.dispatch;

/**
 * 动态分配
 * java是一门静态多分配和动态单分配的语言
 */
public class Dispatch {

    static class QQ{}
    static class YY{}

    public static class Father{
        public void hardChoice(QQ arg){
            System.out.println("Father choose qq");
        }

        public void hardChoice(YY yy){
            System.out.println("Father choose yy");
        }
    }

    public static class Son extends Father{
        public void hardChoice(QQ arg){
            System.out.println("son choose qq");
        }

        public void hardChoice(YY yy){
            System.out.println("son choose yy");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new QQ());
        son.hardChoice(new YY());
    }
}
