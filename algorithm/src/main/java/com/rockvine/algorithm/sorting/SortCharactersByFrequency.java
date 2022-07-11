package com.rockvine.algorithm.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author rocky
 * @date 2022-07-11 22:05
 * @description 451.根据字符出现频率排序（https://leetcode.cn/problems/sort-characters-by-frequency/）
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String ans = solution.frequencySort("cccaaa");
        System.out.println(ans);
    }

    static class Solution {
        public String frequencySort(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }
            List<Character> list = new ArrayList<>(map.keySet());
            list.sort((a, b) -> map.get(b) - map.get(a));

            StringBuilder builder = new StringBuilder();
            for(Character character : list) {
                Integer count = map.get(character);
                while (count-- > 0) {
                    builder.append(character);
                }
            }
            return builder.toString();
        }
    }
}
