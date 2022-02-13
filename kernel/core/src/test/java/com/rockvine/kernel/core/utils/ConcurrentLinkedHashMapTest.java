package com.rockvine.kernel.core.utils;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import com.googlecode.concurrentlinkedhashmap.Weighers;
import com.rockvine.kernel.core.BaseTest;

/**
 * @author rocky
 * @date 2022-02-13 13:54
 * @description
 */
public class ConcurrentLinkedHashMapTest extends BaseTest {

    public static void main(String[] args) {
        testConcurrentLinkedHashMap1();
//        2: 2
//        3: 3
//        4: 4

        testConcurrentLinkedHashMap2();
//        key=2, value=2数据被丢弃了...
//        key=3, value=3数据被丢弃了...
//        1: 1
//        4: 4
//        5: 5
    }

    private static void testConcurrentLinkedHashMap1() {
        ConcurrentLinkedHashMap<String, String> map = new ConcurrentLinkedHashMap.Builder<String, String>()
                .maximumWeightedCapacity(3)
                .weigher(Weighers.singleton())
                .build();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private static void testConcurrentLinkedHashMap2() {
        EvictionListener<String, String> listener = new EvictionListener<String, String>() {
            @Override
            public void onEviction(String key, String value) {
                System.out.println("key=" + key + ", value=" + value + "数据被丢弃了...");
            }
        };

        ConcurrentLinkedHashMap<String, String> map = new ConcurrentLinkedHashMap.Builder<String, String>()
                .maximumWeightedCapacity(3)
                .weigher(Weighers.singleton())
                .listener(listener)
                .build();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.get("1");
        map.put("4", "4");
        map.put("5", "5");

        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}