package com.haisun.simple.designpatterns.proxy.v10;

import java.lang.reflect.Method;

/**
 * 测试m.invoke
 */
public class Test {

    public void test() {
        System.out.println("my test print ~~~");
    }

    public static void main(String[] args) {
        try {
            Method m = Class.forName("com.haisun.simple.designpatterns.proxy.v10.Test").getMethod("test");
            System.out.println(m.getName());
            m.invoke(new Test(), (Object[])null);
        } catch (Exception e) {

        }
    }
}
