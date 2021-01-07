package com.haisun.simple.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile保证不了原子性
 */
public class T4 {
    volatile int count = 0;
    void m(){
        for (int i = 0; i < 10000; i++) {
            count++;    //count++不是原子性
        }
    }

    public static void main(String[] args) {
        T4 t4 = new T4();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t4::m,"thread--" + i));
        }
        for (Thread thread: threads) {
            thread.start();
        }
        for (Thread thread: threads) {
            try {
                thread.join();      //等待所有的线程结束
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(t4.count);
    }

}
