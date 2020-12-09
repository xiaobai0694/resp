package com.haisun.simple.thread.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {

        Thread t = new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 4) {
                    LockSupport.park();
                }
            }
        });

        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t);
    }
}
