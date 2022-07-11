package com.rockvine.algorithm.sorting.common;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-11 21:59
 * @description 选择排序
 */
public class SelectionSort {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 9, 5, 2, 8, 6, 5};
        solution.selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void selectionSort(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int index = i;
                for (int j = i; j < nums.length; j++) {
                    if (nums[index] > nums[j]) {
                        index = j;
                    }
                }
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
    }
}
