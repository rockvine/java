package com.rockvine.concurrency.locks.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author rocky
 * @date 2022-06-12 13:22
 * @description 查看对象头布局
 */
@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public class App {
    public static void main(String[] args) {
        L l = new L();
        // 转化成16进制，方便比较
        System.out.println(ClassLayout.parseInstance(l).toPrintable());

        synchronized (l) {
            System.out.println(ClassLayout.parseInstance(l).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }
}
