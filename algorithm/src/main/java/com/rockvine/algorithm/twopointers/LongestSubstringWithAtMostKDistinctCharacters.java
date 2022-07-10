package com.rockvine.algorithm.twopointers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rocky
 * @date 2022-07-03 11:42
 * @description 340.至多包含K个不同字符的最长子串（https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/）
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.lengthOfLongestSubstringKDistinct("eceba", 2);
        System.out.println(ans);
    }

    static class Solution {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int ans = 0;
            Map<Character, Integer> map = new HashMap<>();

            for (int left = 0, right = 0; right < s.length(); right++) {
                map.put(s.charAt(right), right);
                // 当map中元素个数不满足大于要求的k时，移除区间内最右下标最小的元素，以满足条件
                if (map.size() > k) {
                    int min = Collections.min(map.values());
                    map.remove(s.charAt(min));
                    left = min + 1;
                }
                ans = Math.max(ans, right - left + 1);
            }
            return ans;
        }
    }
}
