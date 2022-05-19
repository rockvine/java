package com.rockvine.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * @date 2022-05-17 23:18
 * @description 运行时常量池溢出
 */
@SuppressWarnings("all")
public class RuntimeConstantPoolOOM {
    /*
     * JVM参数配置：-Xmx5m
     */
    public static void main(String[] args) {
        testRuntimeConstantPoolOOM();
    }

    private static void testRuntimeConstantPoolOOM() {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i).intern());
        }
    }
}
