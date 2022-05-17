package com.rockvine.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * @date 2022-05-17 23:09
 * @description 堆溢出
 */
public class HeapOOMTest {

    /*
     * JVM参数配置：-Xms5m -Xmx5m -XX:+PrintGC
     */
    public static void main(String[] args) {
        testHeapOOM1();

//        testHeapOOM2();
    }

    private static void testHeapOOM1() {
        String[] arr = new String[6*1024*1024];    // 6m
    }

    private static void testHeapOOM2() {
        List<Object> list = new ArrayList<>();
        while (true){
            list.add(new Object());
        }
    }
}