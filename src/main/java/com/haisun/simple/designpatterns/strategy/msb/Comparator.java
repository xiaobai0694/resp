package com.haisun.simple.designpatterns.strategy.msb;

@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);

    default void m() {
        System.out.println("m");
    }
}
