package com.haisun.simple.thread;

public class A1B2C3Thread {

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5,6,7,8,9};
        String strs[] = {"A","B","C","D","E","F","G","H","I"};

        Object o = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (int num: nums) {
                        System.out.print(num);
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        },"t1").start();


        new Thread(()->{
            synchronized (o) {
                for (String str: strs) {
                    System.out.print(str);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t2").start();
    }
}
