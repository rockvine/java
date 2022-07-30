package com.rockvine.concurrency.thread.join;

/**
 * @author rocky
 * @date 2022-07-20 22:35
 * @description 保护性暂停模式
 */
public class GuardedObject {

    private Object response;

    public synchronized Object getResponse() {
        while (response == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public synchronized void setResponse(Object response) {
        this.response = response;
        notifyAll();
    }
}
