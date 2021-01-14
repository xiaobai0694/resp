package com.haisun.simple.designpatterns.builder;

public class Main {
    public static void main(String[] args) {
        Person p = new Person.PersonBuilder()
                .basicInfo(1, "zhangsan", 18)
                //.score(20)
                .weight(200)
                //.loc("bj", "23")
                .build();
    }
}
