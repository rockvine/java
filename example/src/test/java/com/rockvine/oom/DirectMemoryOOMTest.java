package com.rockvine.oom;

import java.nio.ByteBuffer;

/**
 * @author rocky
 * @date 2022-05-17 23:25
 * @description 直接内存溢出
 */
public class DirectMemoryOOMTest {

    /*
     * JVM参数配置：-XX:MacDirectMemorySize=10m
     */
    public static void main(String[] args) {
        testDirectMemoryOOM();
    }

    @SuppressWarnings("unused")
    private static void testDirectMemoryOOM() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12*1024*1024);
    }
}
