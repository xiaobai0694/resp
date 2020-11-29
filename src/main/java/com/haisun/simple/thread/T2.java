package com.haisun.simple.thread;

import java.util.concurrent.TimeUnit;

public class T2 {

    private synchronized void m1 (){
        System.out.println("m1---->start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1---->end");
    }

    private synchronized void m2() {
        System.out.println("m2---->start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2---->end");
    }
    public static void main(String[] args) {
        T2 t2 = new T2();
        new Thread(t2 ::m1,"t2").start();
    }
}
