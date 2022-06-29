package com.rockvine.algorithm.greedy;

/**
 * @author rocky
 * @date 2022-06-29 23:24
 * @description 非递减数列
 */
public class NonDecreasingArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.checkPossibility(new int[]{4, 2, 3});
        System.out.println(b);
    }

    static class Solution {
        public boolean checkPossibility(int[] nums) {
            for (int i = 1, count = 0; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    if (i != 1 && i != nums.length - 1 && nums[i] < nums[i - 2]) {
                        nums[i] = nums[i - 1];
                    }
                    if (++count > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
