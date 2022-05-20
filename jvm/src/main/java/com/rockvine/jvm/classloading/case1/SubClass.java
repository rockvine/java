package com.rockvine.jvm.classloading.case1;

/**
 * @author rocky
 * @date 2022-05-17 16:15
 * @description 类加载测试，子类引用父类的静态字段，子类
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init...");
    }
}
