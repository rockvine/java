package com.rockvine.concurrency.locks;

import com.rockvine.concurrency.locks.jol.L;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author rocky
 * @date 2022-07-17 14:29
 * @description 轻量级锁
 */
public class LightWeightLock {
    public static void main(String[] args) throws InterruptedException {
        L l = new L();

        Thread t1 = new Thread(() -> {
            synchronized (l) {
                System.out.println(ClassLayout.parseInstance(l).toPrintable());
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (l) {
                System.out.println(ClassLayout.parseInstance(l).toPrintable());
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
