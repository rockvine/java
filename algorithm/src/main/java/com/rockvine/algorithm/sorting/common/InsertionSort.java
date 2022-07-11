package com.rockvine.algorithm.sorting.common;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-11 21:57
 * @description 插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 9, 5, 2, 8, 6, 5};
        solution.insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void insertionSort(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                    int temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }
}
