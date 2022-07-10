package com.rockvine.algorithm.binarysearch;

/**
 * @author rocky
 * @date 2022-07-10 21:29
 * @description 69.x的平方根（https://leetcode.cn/problems/sqrtx/）
 */
public class Sqrtx {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.mySqrt(8);
        System.out.println(ans);
    }

    static class Solution {
        public int mySqrt(int x) {
            int left = 0, right = x, ans = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if ((long) mid * mid <= x) {
                    ans = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return ans;
        }
    }
}
