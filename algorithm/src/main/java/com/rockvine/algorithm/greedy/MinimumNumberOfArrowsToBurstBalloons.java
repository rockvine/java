package com.rockvine.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rocky
 * @date 2022-06-27 22:08
 * @description 用最少数量的箭引爆气球（https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/）
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int minArrowShots = solution.findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}});
        System.out.println(minArrowShots);
    }

    static class Solution {
        public int findMinArrowShots(int[][] points) {
            // 最多需要的弓箭数
            int ans = points.length;
            // 将区间按照结尾的大小进行增序排序
            List<int[]> pointList = Arrays.stream(points).sorted(Comparator.comparingInt(point -> point[1])).collect(Collectors.toList());
            int preIndex = 0;
            for (int i = 1; i < pointList.size(); i++) {
                // 比较后一个区间是否和前一个区间重叠，重叠则可节约一支弓箭
                if (pointList.get(i)[0] <= pointList.get(preIndex)[1]) {
                    ans--;
                    continue;
                }
                preIndex = i;
            }
            return ans;
        }
    }
}
