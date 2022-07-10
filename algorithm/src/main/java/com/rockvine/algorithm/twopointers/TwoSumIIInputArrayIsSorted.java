package com.rockvine.algorithm.twopointers;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-06-29 23:40
 * @description 167.两数之和 II - 输入有序数组（https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/）
 */
public class TwoSumIIInputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.twoSum(new int[]{2, 3, 4}, 6);
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] == target) {
                    break;
                }
                if (numbers[left] + numbers[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }
            return new int[]{left + 1, right + 1};
        }
    }
}
