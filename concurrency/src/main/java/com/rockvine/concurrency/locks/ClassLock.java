package com.rockvine.concurrency.locks;

/**
 * @author rocky
 * @date 2022-06-19 11:46
 * @description 类锁
 */
public class ClassLock {
    public static void main(String[] args) {
        new Thread(TestClassLock::test1).start();

        TestClassLock classLock = new TestClassLock();
        new Thread(classLock::test2).start();
    }

    static class TestClassLock {
        public static synchronized void test1() {
            System.out.println("test1..." + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void test2() {
            synchronized (TestClassLock.class) {
                System.out.println("test2..." + System.currentTimeMillis());
            }
        }
    }
}
