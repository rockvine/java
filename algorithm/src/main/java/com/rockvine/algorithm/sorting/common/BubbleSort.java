package com.rockvine.algorithm.sorting.common;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-11 21:58
 * @description 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 9, 5, 2, 8, 6, 5};
        solution.bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void bubbleSort(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length - i - 1; j++) {
                    if (nums[j] > nums[j + 1]) {
                        int temp = nums[j];
                        nums[j] = nums[j + 1];
                        nums[j + 1] = temp;
                    }
                }
            }
        }
    }
}
