package com.rockvine.concurrency.thread.join;

/**
 * @author rocky
 * @date 2022-07-20 22:41
 * @description 保护性暂停模式示例
 */
public class GuardedObjectApp {
    public static void main(String[] args) throws InterruptedException {
        GuardedObject guardedObject = new GuardedObject();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.setResponse("Hello World");
        }).start();

        Object response = guardedObject.getResponse();
        System.out.println(response);
    }
}
