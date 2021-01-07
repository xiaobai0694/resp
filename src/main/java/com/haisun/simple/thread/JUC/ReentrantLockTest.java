package com.haisun.simple.thread.JUC;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        WeakHashMap<String,Object> weakMap = new WeakHashMap();


    }
}

