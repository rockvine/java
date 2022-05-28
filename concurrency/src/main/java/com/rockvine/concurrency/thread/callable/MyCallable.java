package com.rockvine.concurrency.thread.callable;

import java.util.concurrent.Callable;

/**
 * @author rocky
 * @date 2022-05-28 18:59
 * @description callable线程启动
 */
class MyCallable implements Callable<String> {
    @Override
    public String call() {
        return "success";
    }
}
