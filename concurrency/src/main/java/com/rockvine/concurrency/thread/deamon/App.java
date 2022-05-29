package com.rockvine.concurrency.thread.deamon;

/**
 * @author rocky
 * @date 2022-05-25 16:39
 * @description 守护线程
 */
@SuppressWarnings("Convert2Lambda")
public class App {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("thread...");
                    } finally {
                        System.out.println("finally...");
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
