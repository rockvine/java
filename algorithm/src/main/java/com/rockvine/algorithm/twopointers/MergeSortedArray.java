package com.rockvine.algorithm.twopointers;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-06-29 23:41
 * @description 88.合并两个有序数组（https://leetcode.cn/problems/merge-sorted-array/）
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        solution.merge(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }

    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int pos = nums1.length;
            while (m > 0 && n > 0) {
                nums1[--pos] = nums1[m - 1] > nums2[n - 1] ? nums1[--m] : nums2[--n];
            }
            while (n > 0) {
                nums1[--pos] = nums2[--n];
            }
        }
    }
}
