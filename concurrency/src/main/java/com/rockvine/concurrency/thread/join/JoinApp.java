package com.rockvine.concurrency.thread.join;

/**
 * @author rocky
 * @date 2022-05-20 18:57
 * @description join方法示例
 */
@SuppressWarnings("Convert2Lambda")
public class JoinApp {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                    System.out.println("thread...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        System.out.println("main...");
    }
}