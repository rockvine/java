package com.rockvine.concurrency.thread.interrupt.thread;

/**
 * @author rocky
 * @date 2022-05-25 21:09
 * @description 线程中止测试类
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(1);    // 睡眠1ms，使子线程进入sleep后再进行中断
        thread.interrupt();
    }
}


