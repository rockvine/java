package com.rockvine.jvm.classloading.case1;

/**
 * @author rocky
 * @date 2022-05-17 16:14
 * @description 类加载测试，子类引用父类的静态字段，父类
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init...");
    }

    public static int value = 123;
}
