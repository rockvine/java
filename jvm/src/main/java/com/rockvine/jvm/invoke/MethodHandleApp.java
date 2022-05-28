package com.rockvine.jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author rocky
 * @date 2022-05-18 13:34
 * @description 方法句柄
 */
public class MethodHandleApp {
    static class Dog {
        protected String sayHello() {
            return "Woof Woof...";
        }
    }

    static class Human {
        protected String sayHello() {
            return "Hello, Guy";
        }
    }

    static class Man extends Human {
        @Override
        protected String sayHello() {
            return "Hello, Gentleman";
        }
    }

    static String sayHello(Object obj) throws Throwable {
        // 从工厂方法中获取方法句柄
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 创建MethodType，获取指定方法的签名（出参和入参）
        MethodType methodType = MethodType.methodType(String.class);
        // 获取具体的MethodHandle
        MethodHandle methodHandle = lookup.findVirtual(obj.getClass(), "sayHello", methodType);

        return (String) methodHandle.invoke(obj);
    }

    public static void main(String[] args) throws Throwable {
        System.out.println(MethodHandleApp.sayHello(new Dog()));
        System.out.println(MethodHandleApp.sayHello(new Human()));
        System.out.println(MethodHandleApp.sayHello(new Man()));
    }
}
