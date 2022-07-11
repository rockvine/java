package com.rockvine.algorithm.sorting.common;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-11 21:56
 * @description 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 9, 5, 2, 8, 6, 5};
        solution.mergeSort(nums, 0, nums.length, new int[nums.length]);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void mergeSort(int[] nums, int l, int r, int[] temp) {
            if (l + 1 >= r) {
                return;
            }
            int m = l + (r - l) / 2;
            mergeSort(nums, l, m, temp);
            mergeSort(nums, m, r, temp);

            int p = l, q = m, i = l;
            while (p < m || q < r) {
                if (q >= r || (p < m && nums[p] <= nums[q])) {
                    temp[i++] = nums[p++];
                } else {
                    temp[i++] = nums[q++];
                }
            }
            System.arraycopy(temp, l, nums, l, r - l);
        }
    }
}
