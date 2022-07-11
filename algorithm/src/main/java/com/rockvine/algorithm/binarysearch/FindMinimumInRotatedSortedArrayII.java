package com.rockvine.algorithm.binarysearch;

/**
 * @author rocky
 * @date 2022-07-10 21:40
 * @description 154.寻找旋转排序数组中的最小值 II（https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/）
 */
public class FindMinimumInRotatedSortedArrayII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int min = solution.findMin(new int[]{1, 3, 5});
        System.out.println(min);
    }

    static class Solution {
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < nums[right]) {
                    right = mid;
                } else if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else {
                    right--;
                }
            }
            return nums[left];
        }
    }
}
