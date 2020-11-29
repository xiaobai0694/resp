package com.haisun.simple.thread;

import java.util.concurrent.TimeUnit;

public class Account {
    private String name;
    private double balance;

    public void set(String name, double balance) {
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public double get(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.set("zhangsan", 100.00);
            }
        }
        ).start();

        System.out.println(account.get("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.get("zhangsan"));
    }
}
