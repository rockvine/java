package com.rockvine.algorithm.greedy;

import java.util.Arrays;

/**
 * @author rocky
 * @date 2022-06-24 22:14
 * @description 分发饼干（https://leetcode.cn/problems/assign-cookies/）
 */
public class AssignCookies {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int contentChildren = solution.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3});
        System.out.println(contentChildren);
    }

    static class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int child = 0, cookie = 0;
            // 优先给最小饥饿度的孩子分配最小的饼干
            while (child < g.length && cookie < s.length) {
                if (g[child] <= s[cookie]) {
                    child++;
                }
                cookie++;
            }
            return child;
        }
    }
}