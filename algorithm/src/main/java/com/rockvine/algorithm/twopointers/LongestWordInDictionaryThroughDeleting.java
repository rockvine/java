package com.rockvine.algorithm.twopointers;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author rocky
 * @date 2022-07-03 11:38
 * @description 524.通过删除字母匹配到字典里最长单词（https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting/）
 */
public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String longestWord = solution.findLongestWord("abpcplea", Lists.newArrayList("ale", "apple", "monkey", "plea"));
        System.out.println(longestWord);
    }

    static class Solution {
        public String findLongestWord(String s, List<String> dictionary) {
            String longestWord = "";
            for (String word : dictionary) {
                int i = 0, j = 0;
                while (i < s.length() && j < word.length()) {
                    if (s.charAt(i) == word.charAt(j)) {
                        j++;
                    }
                    i++;
                }
                if (j == word.length()) {
                    if (word.length() > longestWord.length()
                            || (word.length() == longestWord.length() && word.compareTo(longestWord) < 0)) {
                        longestWord = word;
                    }
                }
            }
            return longestWord;
        }
    }
}
