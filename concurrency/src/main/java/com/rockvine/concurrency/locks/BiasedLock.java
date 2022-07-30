package com.rockvine.concurrency.locks;

import com.rockvine.concurrency.locks.jol.L;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author rocky
 * @date 2022-07-17 13:53
 * @description 偏向锁加锁
 */
public class BiasedLock {
    public static void main(String[] args) {
        L l = new L();
        new Thread(() -> {
            synchronized (l) {
                System.out.println(ClassLayout.parseInstance(l).toPrintable());
            }
        }).start();
    }
}
