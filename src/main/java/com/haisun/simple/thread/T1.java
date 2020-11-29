package com.haisun.simple.thread;

public class T1 implements  Runnable{

    private int count = 10;

    @Override
    public void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i< 5 ; i++) {
            T1 t = new T1();
            new Thread(t,"t" + i).start();
        }
    }
}
