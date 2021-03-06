package com.rockvine.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @author rocky
 * @date 2022-05-17 23:25
 * @description 直接内存溢出
 */
public class DirectMemoryOOM {
    /*
     * JVM参数配置：-XX:MacDirectMemorySize=10m
     */
    public static void main(String[] args) {
        testDirectMemoryOOM();
    }

    private static void testDirectMemoryOOM() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12*1024*1024);
    }
}
