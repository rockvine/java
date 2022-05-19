package com.rockvine.deadlock;

/**
 * @author rocky
 * @date 2022-05-18 22:32
 * @description 死锁
 */
public class Client {
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::ab).start();
        new Thread(deadLock::ba).start();
    }
}

@SuppressWarnings("all")
class DeadLock {
    private Object a = new Object(), b = new Object();

    public void ab() {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() + "拿到了a锁，准备拿b锁...");

            try {
                Thread.sleep(100);  //休眠100毫秒，让ba方法有机会拿到b锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "拿到了b锁...");
            }
        }
    }

    public void ba() {
        synchronized (b) {
            System.out.println(Thread.currentThread().getName() + "拿到了b锁，准备拿a锁...");
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "拿到了a锁...");
            }
        }
    }
}
