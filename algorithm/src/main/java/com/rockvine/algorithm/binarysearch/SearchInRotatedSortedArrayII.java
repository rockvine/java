package com.rockvine.algorithm.binarysearch;

/**
 * @author rocky
 * @date 2022-07-10 23:35
 * @description 81.搜索旋转排序数组 II（https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/）
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0);
        System.out.println(b);
    }

    static class Solution {
        public boolean search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[left] == target || nums[mid] == target) {
                    return true;
                }
                if (nums[mid] < nums[right]) {      // 右区间是增序的
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else if (nums[mid] > nums[left]) {    // 左区间是增序的
                    if (nums[mid] > target && target >= nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {    // 无法判断哪个区间是增序的
                    left++;
                }
            }
            return false;
        }
    }
}
