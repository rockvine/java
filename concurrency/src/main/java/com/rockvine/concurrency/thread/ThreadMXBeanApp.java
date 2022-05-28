package com.rockvine.concurrency.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author rocky
 * @date 2022-05-25 16:23
 * @description 利用ThreadMXBean查看线程示例
 */
public class ThreadMXBeanApp {
    public static void main(String[] args) {
        // ThreadMXBean虚拟机线程管理的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "..." + threadInfo.getThreadName());
        }
    }
}

