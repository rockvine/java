package com.rockvine.concurrency.thread.interrupt.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author rocky
 * @date 2022-05-28 19:02
 * @description 线程中止测试类
 */
public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        Thread.sleep(1000);    // 睡眠1ms，让子线程启动
        boolean cancel = futureTask.cancel(true);
        System.out.println(cancel);
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.isCancelled());
        System.out.println(futureTask.get());
    }
}
