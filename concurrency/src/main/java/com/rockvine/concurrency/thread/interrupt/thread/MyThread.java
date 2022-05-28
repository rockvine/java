package com.rockvine.concurrency.thread.interrupt.thread;

/**
 * @author rocky
 * @date 2022-05-28 18:57
 * @description 线程中止测试
 */
@SuppressWarnings("all")
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5 && !isInterrupted(); i++) {
            System.out.println("i=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(isInterrupted());
                interrupt();
            }
        }
    }
}