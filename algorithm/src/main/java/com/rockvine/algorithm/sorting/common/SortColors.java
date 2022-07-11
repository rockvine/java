package com.rockvine.algorithm.sorting.common;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-11 22:06
 * @description 75.颜色分类（https://leetcode.cn/problems/sort-colors/）
 */
public class SortColors {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void sortColors(int[] nums) {
            int p0 = 0, p1 = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, p0);
                    if (p0 == p1) {
                        p1++;
                    }
                    p0++;
                }
                if (nums[i] == 1) {
                    swap(nums, i, p1++);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
