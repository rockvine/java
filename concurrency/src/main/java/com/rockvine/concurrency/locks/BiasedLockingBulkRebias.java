package com.rockvine.concurrency.locks;

import com.rockvine.concurrency.locks.jol.L;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * @date 2022-07-17 16:55
 * @description 批量重偏向
 */
@SuppressWarnings({"EmptySynchronizedStatement", "SynchronizationOnLocalVariableOrMethodParameter"})
public class BiasedLockingBulkRebias {
    public static void main(String[] args) throws InterruptedException {
        List<L> locks = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                L l = new L();
                locks.add(l);
                synchronized (l) {
                    // do nothing
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                L l = locks.get(i);
                synchronized (l) {
                    if (i == 18 || i == 19) {
                        System.out.println(ClassLayout.parseInstance(l).toPrintable());
                    }
                }
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
