package com.haisun.simple.designpatterns.proxy.v10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 此类实际是由Proxy自动生成
 */
public class $Proxy0 extends Proxy implements Movable {
    private static Method m0;
    private static Method m1;
    private static Method m2;
    private static Method m3;

    public $Proxy0(InvocationHandler var1) throws Exception {
        super(var1);
    }

    public final void move(Object var1) throws Exception {
        try {
            super.h.invoke(this,m3,(Object[])null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("com.haisun.simple.designpatterns.proxy.v10.Movable").getMethod("move");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {

    }
}
