package com.rockvine.algorithm.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rocky
 * @date 2022-07-01 01:01
 * @description 76.最小覆盖子串（https://leetcode.cn/problems/minimum-window-substring/）
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }

    static class Solution {
        public String minWindow(String s, String t) {
            // 统计t中字符情况
            HashMap<Character, Integer> tMap = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                tMap.put(c, tMap.getOrDefault(c, 0) + 1);
            }
            // 用于统计滑动窗口中的字符情况
            HashMap<Character, Integer> sMap = new HashMap<>();

            String minSubString = "";
            // 移动右指针，不断修改滑动窗口中的统计数据
            for (int left = 0, right = 0; right < s.length(); right++) {
                sMap.put(s.charAt(right), sMap.getOrDefault(s.charAt(right), 0) + 1);
                // 若目前滑动窗口已包含t中所有字符，则尝试移动左指针，在不影响结果的情况下获取最短子字符串
                while (checkSMap(sMap, tMap)) {
                    String substring = s.substring(left, right + 1);
                    minSubString = "".equals(minSubString) ? substring
                            : minSubString.length() < substring.length() ? minSubString : substring;
                    sMap.put(s.charAt(left), sMap.get(s.charAt(left++)) - 1);
                }
            }
            return minSubString;
        }

        private boolean checkSMap(HashMap<Character, Integer> sMap, HashMap<Character, Integer> tMap) {
            for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
                if (sMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    return false;
                }
            }
            return true;
        }
    }
}
