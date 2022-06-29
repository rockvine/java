package com.rockvine.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * @date 2022-06-27 22:11
 * @description 划分字母区间
 */
public class PartitionLabels {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> partitionLabels = solution.partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(partitionLabels);
    }

    static class Solution {
        public List<Integer> partitionLabels(String s) {
            // 统计每个字母最后一次出现的下标位置
            int[] last = new int[26];
            for (int i = 0; i < s.length(); i++) {
                last[s.charAt(i) - 'a'] = i;
            }
            List<Integer> partition  = new ArrayList<>();
            int start = 0, end = 0;
            // 从左到右遍历字符串，获取当前片段所有字母最后一次出现的最大值end
            for (int i = 0; i < s.length(); i++) {
                end = Math.max(last[s.charAt(i) - 'a'], end);
                // 当访问到下标end时，当前片段访问结束
                if (i == end) {
                    partition .add(end - start + 1);
                    start = end + 1;
                }
            }
            return partition ;
        }
    }
}
