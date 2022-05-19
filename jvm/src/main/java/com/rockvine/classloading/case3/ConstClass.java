package com.rockvine.classloading.case3;

/**
 * @author rocky
 * @date 2022-05-17 16:28
 * @description
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init...");
    }

    public static final String HELLO_WORLD = "Hello World";
}
