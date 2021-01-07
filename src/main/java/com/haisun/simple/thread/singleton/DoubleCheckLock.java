package com.haisun.simple.thread.singleton;

import java.util.concurrent.TimeUnit;

public class DoubleCheckLock {

    private static /*volatile*/ DoubleCheckLock INSTANCE;

    private DoubleCheckLock(){}

    public static DoubleCheckLock getInstance(){
        if(null == INSTANCE) {
            synchronized (DoubleCheckLock.class) {
                if (null == INSTANCE) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new DoubleCheckLock();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0;i <100; i++) {
            new Thread(()->{
                System.out.println(DoubleCheckLock.getInstance().hashCode());
            }).start();
        }
    }
}
