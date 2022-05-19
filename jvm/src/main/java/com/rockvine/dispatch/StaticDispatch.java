package com.rockvine.dispatch;

/**
 * @author rocky
 * @date 2022-05-18 13:05
 * @description 静态分派
 */
public class StaticDispatch {

    static abstract class Human {}

    static class Man extends Human {}

    static class Woman extends Human {}

    public void sayHello(Human guy) {
        System.out.println("Hello, Guy");
    }

    public void sayHello(Man guy) {
        System.out.println("Hello, Gentleman");
    }

    public void sayHello(Woman guy) {
        System.out.println("Hello, Lady");
    }

    public static void main(String[] args) {
        Human h1 = new Man();
        Human h2 = new Woman();

        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(h1);
        sr.sayHello(h2);
    }
}
