package com.rockvine.classloading.case1;

/**
 * @author rocky
 * @date 2022-05-17 16:14
 * @description
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init...");
    }

    public static int value = 123;
}
