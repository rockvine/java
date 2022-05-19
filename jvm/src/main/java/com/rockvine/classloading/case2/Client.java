package com.rockvine.classloading.case2;

/**
 * @author rocky
 * @date 2022-05-17 16:24
 * @description 通过数组定义来引入类，不会触发此类的初始化
 */
public class Client {
    public static void main(String[] args) {
        SuperClass[] classArr = new SuperClass[10];
    }
}
