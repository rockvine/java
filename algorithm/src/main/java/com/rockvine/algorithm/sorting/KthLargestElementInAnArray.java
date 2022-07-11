package com.rockvine.algorithm.sorting;

/**
 * @author rocky
 * @date 2022-07-11 22:01
 * @description 215.数组中的第K个最大元素（https://leetcode.cn/problems/kth-largest-element-in-an-array/）
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(ans);
    }

    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            int l = 0, r = nums.length - 1, target = nums.length - k;
            while (l < r) {
                int index = quickSelection(nums, l, r);
                if (index < target) {
                    l = index + 1;
                } else if (index > target) {
                    r = index - 1;
                } else {
                    return nums[index];
                }
            }
            return nums[l];
        }

        private int quickSelection(int[] nums, int l, int r) {
            int i = l + 1, j = r;
            while (true) {
                while (i < r && nums[i] <= nums[l]) {
                    i++;
                }
                while (j > l && nums[j] >= nums[l]) {
                    j--;
                }
                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            swap(nums, l, j);
            return j;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
