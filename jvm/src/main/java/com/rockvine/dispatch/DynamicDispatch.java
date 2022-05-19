package com.rockvine.dispatch;

/**
 * @author rocky
 * @date 2022-05-18 13:06
 * @description 动态分派
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Hello, Gentleman");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Hello, Lady");
        }
    }

    public static void main(String[] args) {
        Human h1 = new Man();
        Human h2 = new Woman();
        h1.sayHello();
        h2.sayHello();
    }
}
