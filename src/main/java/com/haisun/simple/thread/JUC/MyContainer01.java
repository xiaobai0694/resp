package com.haisun.simple.thread.JUC;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 *  面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *  能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class MyContainer01<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count ++;
        lists.add(t);
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll(); //通知生产者进行生产
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
