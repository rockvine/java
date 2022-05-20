package com.rockvine.jvm.classloading.case2;

/**
 * @author rocky
 * @date 2022-05-17 16:14
 * @description 类加载测试，数组定义来引入类
 */
public class ArrayClass {
    static {
        System.out.println("ArrayClass init...");
    }
}
