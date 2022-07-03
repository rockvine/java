package com.rockvine.algorithm.greedy;

/**
 * @author rocky
 * @date 2022-06-24 22:17
 * @description 分发糖果（https://leetcode.cn/problems/candy/）
 */
public class Candy {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int candy = solution.candy(new int[]{1,2,87,87,87,2,1});
        System.out.println(candy);
    }

    static class Solution {
        public int candy(int[] ratings) {
            // 每个孩子至少一颗糖果
            int candyCount = ratings.length;
            // 用于记录每个孩子需要评分需要额外奖励的糖果数量
            int[] candyArr = new int[ratings.length];
            // 从左往右遍历一遍，如果右边孩子的评分比左边的高，则右边孩子的糖果数更新为左边孩子的糖果数加 1
            for (int i = 1; i < ratings.length; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    candyArr[i] = candyArr[i - 1] + 1;
                    candyCount += candyArr[i];
                }
            }
            /*
             * 从右往左遍历一遍，如果左边孩子的评分比右边的高，
             * 且左边孩子当前的糖果数不大于右边孩子的糖果数（排除上面从左往右遍历过程中已追加的糖果数量影响），
             * 则左边孩子的糖果数更新为右边孩子的糖果数加 1
             */
            for (int i = ratings.length - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1] && candyArr[i] <= candyArr[i + 1]) {
                    // 因该位置孩子的糖果数量较上次遍历又发生了变化，会导致重复计算，故需减去之前的数量，重新计算
                    candyCount -= candyArr[i];
                    candyArr[i] = candyArr[i + 1] + 1;
                    candyCount += candyArr[i];
                }
            }
            return candyCount;
        }
    }
}