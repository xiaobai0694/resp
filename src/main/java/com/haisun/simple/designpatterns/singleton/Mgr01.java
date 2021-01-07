package com.haisun.simple.designpatterns.singleton;

/**
 * 单例模式
 */
public class Mgr01 {
    private static Mgr01 mgr01 = new Mgr01();

    private Mgr01(){

    }

    public static Mgr01  getInstance(){
        return mgr01;
    }

    public static void main(String[] args) {
        Mgr01 m01 = Mgr01.getInstance();
        Mgr01 m02 = Mgr01.getInstance();

        System.out.println(m01 == m02);
    }
}
