package com.rockvine.algorithm.sorting.common;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-11 21:53
 * @description 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 9, 5, 2, 8, 6, 5};
        solution.quickSort(nums, 0, nums.length);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void quickSort(int[] nums, int l, int r) {
            if (l + 1 >= r) {
                return;
            }
            int first = l, last = r - 1, key = nums[first];
            while (first < last) {
                while (first < last && nums[last] >= key) {
                    last--;
                }
                nums[first] = nums[last];
                while (first < last && nums[first] <= key) {
                    first++;
                }
                nums[last] = nums[first];
            }
            nums[first] = key;
            quickSort(nums, l, first);
            quickSort(nums, first + 1, r);
        }
    }
}
