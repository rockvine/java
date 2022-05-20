package com.rockvine.jvm.classloading.case3;

/**
 * @author rocky
 * @date 2022-05-17 16:29
 * @description 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 */
public class App {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLO_WORLD);
    }
}
