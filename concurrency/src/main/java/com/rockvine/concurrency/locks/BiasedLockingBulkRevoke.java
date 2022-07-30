package com.rockvine.concurrency.locks;

import com.rockvine.concurrency.locks.jol.L;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * @date 2022-07-17 16:54
 * @description 批量撤销
 */
@SuppressWarnings({"EmptySynchronizedStatement", "SynchronizationOnLocalVariableOrMethodParameter"})
public class BiasedLockingBulkRevoke {
    public static void main(String[] args) throws InterruptedException {
        List<L> locks = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                L l = new L();
                locks.add(l);
                synchronized (l) {
                    // do nothing
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                L l = locks.get(i);
                synchronized (l) {
                    // do nothing
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                L l = locks.get(i);
                synchronized (l) {
                    if (i == 37 || i == 38) {
                        L nl = new L();
                        System.out.println(ClassLayout.parseInstance(nl).toPrintable());
                    }
                }
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }
}
