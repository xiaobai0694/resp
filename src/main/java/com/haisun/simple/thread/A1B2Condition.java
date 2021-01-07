package com.haisun.simple.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A1B2交替输出，先输出字母
 */
public class A1B2Condition {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5,6,7,8,9};
        String strs[] = {"A","B","C","D","E","F","G","H","I"};

        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        Object o = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    lock.lock();
                    for (int num: nums) {
                        System.out.print(num);
                        c2.signal();
                        c1.await();
                    }
                    c2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        },"t1").start();


        new Thread(()->{
            try {
                lock.lock();
                for (String str : strs) {
                    System.out.print(str);
                    latch.countDown();
                    c1.signal();
                    c2.await();

                }
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
