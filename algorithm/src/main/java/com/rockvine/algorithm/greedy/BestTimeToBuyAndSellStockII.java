package com.rockvine.algorithm.greedy;

/**
 * @author rocky
 * @date 2022-06-27 22:13
 * @description 买卖股票的最佳时机 II（https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/）
 */
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(i);
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                // 统计利润大于0的区间
                ans += Math.max(0, prices[i] - prices[i - 1]);
            }
            return ans;
        }
    }
}
