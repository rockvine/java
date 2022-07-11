package com.rockvine.algorithm.sorting;

import java.util.*;

/**
 * @author rocky
 * @date 2022-07-11 22:02
 * @description 347.前K个高频元素（https://leetcode.cn/problems/top-k-frequent-elements/）
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(ans));
    }

    @SuppressWarnings("ConstantConditions")
    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (queue.size() == k) {
                    if (queue.peek().getValue() < entry.getValue()) {
                        queue.poll();
                        queue.offer(entry);
                    }
                } else {
                    queue.offer(entry);
                }
            }

            int[] ans = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                ans[i] = queue.poll().getKey();
            }
            return ans;
        }
    }
}
