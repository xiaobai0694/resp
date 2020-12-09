package com.haisun.simple.thread.JUC;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer02<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    ReentrantLock lock = new ReentrantLock();
    Condition producer  = lock.newCondition();
    Condition consumer = lock.newCondition();


    public void put(T t) {
        try {
            lock.lock();
            while(lists.size() == MAX) { //想想为什么用while而不是用if？
                producer.await();
            }

            lists.add(t);
            ++count;
            consumer.signalAll(); //通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while(lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count --;
            producer.signalAll(); //通知生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;

    }

    public static void main(String[] args) {
        MyContainer01<String> c = new MyContainer01<>();
        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() +" " + j);
                }
            },"生产者：" + i).start();

        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    System.out.println(c.get());
                }
            },"消费者：" + i).start();

        }
    }
}
