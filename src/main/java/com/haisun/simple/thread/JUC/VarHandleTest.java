package com.haisun.simple.thread.JUC;

import java.lang.invoke.MethodHandles;

public class VarHandleTest {
    /*int x = 8;

    private static VarHandle handle;

    static {
        try {
            handle = MethodHandles.lookup().findVarHandle(T01_HelloVarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VarHandleTest t = new VarHandleTest();

        //plain read / write
        System.out.println((int)handle.get(t));
        handle.set(t,9);
        System.out.println(t.x);

        handle.compareAndSet(t, 9, 10);
        System.out.println(t.x);

        handle.getAndAdd(t, 10);
        System.out.println(t.x);

    }
*/
}
