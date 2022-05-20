package com.rockvine.jvm.classloading.case3;

/**
 * @author rocky
 * @date 2022-05-17 16:28
 * @description 类加载测试，直接调用常量
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init...");
    }

    public static final String HELLO_WORLD = "Hello World";
}
