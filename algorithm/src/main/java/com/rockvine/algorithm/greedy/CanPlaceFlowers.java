package com.rockvine.algorithm.greedy;

/**
 * @author rocky
 * @date 2022-06-26 16:43
 * @description 种花问题
 */
public class CanPlaceFlowers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.canPlaceFlowers(new int[]{0, 0, 1, 0, 0}, 1);
        System.out.println(b);
    }

    static class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            for (int i = 0; i < flowerbed.length && n > 0; i++) {
                if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                        && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                    n--;
                    flowerbed[i] = 1;
                }
            }
            return n == 0;
        }
    }
}