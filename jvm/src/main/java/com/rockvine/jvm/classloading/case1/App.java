package com.rockvine.jvm.classloading.case1;

/**
 * @author rocky
 * @date 2022-05-17 16:16
 * @description 通过子类引用父类的静态字段，只会触发父类的初始化，不会导致子类初始化（子类只会被加载）
 */
public class App {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
