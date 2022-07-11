package com.rockvine.algorithm.binarysearch;

/**
 * @author rocky
 * @date 2022-07-10 21:56
 * @description 540.有序数组中的单一元素（https://leetcode.cn/problems/single-element-in-a-sorted-array/）
 */
public class SingleElementInASortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8});
        System.out.println(ans);
    }

    static class Solution {
        public int singleNonDuplicate(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] == nums[mid ^ 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return nums[left];
        }
    }
}
