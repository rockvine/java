package com.rockvine.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author rocky
 * @date 2022-06-27 22:14
 * @description 根据身高重建队列
 */
public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ints = solution.reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        System.out.println(Arrays.deepToString(ints));
    }

    @SuppressWarnings("Convert2Lambda")
    static class Solution {
        public int[][] reconstructQueue(int[][] people) {
            // 按照身高从小到大，前面身高更高或者相同的人数从少到多进行排序
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] people0, int[] people1) {
                    if (people0[0] != people1[0]) {
                        return people0[0] - people1[0];
                    } else {
                        return people0[1] - people1[1];
                    }
                }
            });
            // 重新构造的队列
            int[][] ans = new int[people.length][];

            // 遍历重新排序后的队列
            for (int[] person : people) {
                // 需重新入队人员的前面身高更高或者相同的人数，为0时则入队
                int index = person[1];
                // 遍历重新构造的队列（未完全完成）
                for (int j = 0; j < ans.length; j++) {
                    if (index == 0) {
                        if (ans[j] == null) {
                            ans[j] = person;
                            break;
                        }
                        continue;
                    }
                    // 确保需入队人员前面身高更高或者相同的人数要求
                    if (ans[j] == null || ans[j][0] >= person[0]) {
                        index--;
                    }
                }
            }
            return ans;
        }
    }
}
