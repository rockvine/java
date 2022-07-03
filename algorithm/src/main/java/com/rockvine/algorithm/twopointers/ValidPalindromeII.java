package com.rockvine.algorithm.twopointers;

/**
 * @author rocky
 * @date 2022-07-03 11:36
 * @description 验证回文字符串 II（https://leetcode.cn/problems/valid-palindrome-ii/）
 */
public class ValidPalindromeII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.validPalindrome("abca");
        System.out.println(b);
    }

    static class Solution {
        public boolean validPalindrome(String s) {
            int left = 0, right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1);
                }
                left++;
                right--;
            }
            return true;
        }

        private boolean validPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
    }
}
