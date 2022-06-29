package com.rockvine.concurrency.locks;

/**
 * @author rocky
 * @date 2022-06-19 11:22
 * @description 对象锁
 */
public class ObjectLock {
    public static void main(String[] args) {
        TestObjectLock objectLock = new TestObjectLock();
        new Thread(objectLock::test1).start();
        new Thread(objectLock::test2).start();
    }

    static class TestObjectLock {
        public synchronized void test1() {
            System.out.println("test1..." + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void test2() {
            System.out.println("test2..." + System.currentTimeMillis());
        }
    }
}
