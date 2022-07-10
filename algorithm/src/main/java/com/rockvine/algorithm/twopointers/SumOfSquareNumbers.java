package com.rockvine.algorithm.twopointers;

/**
 * @author rocky
 * @date 2022-07-01 01:03
 * @description 633.平方数之和（https://leetcode.cn/problems/sum-of-square-numbers/）
 */
public class SumOfSquareNumbers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.judgeSquareSum(5);
        System.out.println(b);
    }

    static class Solution {
        public boolean judgeSquareSum(int c) {
            int left = 0, right = (int) Math.sqrt(c);
            while (left <= right) {
                int sum = (int) (Math.pow(left, 2) + Math.pow(right, 2));
                if (sum == c) {
                    return true;
                }
                if (sum > c) {
                    right--;
                } else {
                    left++;
                }
            }
            return false;
        }
    }
}
