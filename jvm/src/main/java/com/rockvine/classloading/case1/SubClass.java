package com.rockvine.classloading.case1;

/**
 * @author rocky
 * @date 2022-05-17 16:15
 * @description
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init...");
    }
}
