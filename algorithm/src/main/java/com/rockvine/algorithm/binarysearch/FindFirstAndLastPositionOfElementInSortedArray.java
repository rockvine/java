package com.rockvine.algorithm.binarysearch;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-07-10 21:31
 * @description 34.在排序数组中查找元素的第一个和最后一个位置（https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/）
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(ans));
    }

    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            int lower = lower(nums, target);
            int upper = upper(nums, target);
            if (lower > upper) {
                return new int[]{-1, -1};
            }
            return new int[]{lower, upper};
        }

        private int lower(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

        private int upper(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left - 1;
        }
    }
}
