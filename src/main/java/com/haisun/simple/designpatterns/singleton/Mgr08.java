package com.haisun.simple.designpatterns.singleton;

public enum Mgr08 {
    INSTANCE;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void m(){

    }

    public static void main(String[] args) {
        Mgr08 m08 = Mgr08.INSTANCE;
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr08.INSTANCE.hashCode());
            }).start();
        }
    }
}
