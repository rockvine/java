package com.rockvine.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rocky
 * @date 2022-06-26 16:00
 * @description 无重叠区间（https://leetcode.cn/problems/non-overlapping-intervals/）
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 4}, {1, 3}});
        System.out.println(i);
    }

    static class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length <= 0) {
                return 0;
            }
            // 将区间按照结尾的大小进行增序排序
            List<int[]> intervalList = Arrays.stream(intervals).sorted(Comparator.comparingInt(interval -> interval[1])).collect(Collectors.toList());
            int ans = 0;
            for (int preIndex = 0, i = 1; i < intervalList.size(); i++) {
                // 比较后一个区间开始是否在前一个区间内即可
                if (intervalList.get(i)[0] < intervalList.get(preIndex)[1]) {
                    ans++;
                    continue;
                }
                preIndex = i;
            }
            return ans;
        }
    }
}
