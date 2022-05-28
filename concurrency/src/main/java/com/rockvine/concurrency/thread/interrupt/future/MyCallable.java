package com.rockvine.concurrency.thread.interrupt.future;

import java.util.concurrent.Callable;

/**
 * @author rocky
 * @date 2022-05-28 19:02
 * @description 线程中止测试
 */
class MyCallable implements Callable<String> {
    @Override
    public String call() {
        for (int i = 0; i < 500 && !Thread.currentThread().isInterrupted(); i++) {
            System.out.println(i);
        }
        return "success";
    }
}
