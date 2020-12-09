package com.haisun.simple.thread;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 */
public class T3 {

    private /*volatile*/ boolean isRunning = true;

    void m3 () {
        System.out.println("m3 start------");
        while (isRunning){

        }
        System.out.println("m3 end-------");
    }

    public static void main(String[] args) {
        T3 t3 = new T3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t3.m3();
            }
        }, "t3").start();

        //new Thread(t3::m3).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.isRunning = false;
    }
}
