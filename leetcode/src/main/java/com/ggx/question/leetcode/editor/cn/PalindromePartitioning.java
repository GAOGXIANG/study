package com.ggx.question.leetcode.editor.cn;

//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 深度优先搜索 动态规划 回溯算法 
// 👍 470 👎 0

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioning().new Solution();
        String s = "aab";
        List<List<String>> result = solution.partition(s);
        System.out.println(result.size());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> partition(String s) {

            List<List<String>> result = new ArrayList<>();
            boolean[][] dp = initialPalindrome(s);
            partitionPalindrome(s, 0, dp, result, new ArrayList<>());
            return result;
        }

        //划分回文字符串
        private void partitionPalindrome(String s, int start, boolean[][] dp, List<List<String>> result, List<String> temp) {
            if(start == s.length()){
                result.add(new ArrayList<>(temp));
            }
            for(int i = start; i < s.length(); i++){
                if(dp[start][i] == true){
                    temp.add(s.substring(start, i+1));
                    partitionPalindrome(s, i+1, dp, result, temp);
                    temp.remove(temp.size()-1);
                }
            }
        }

        /**
         * 初始化回文字符串
         */
        private boolean[][] initialPalindrome(String s) {
            boolean[][] dp = new boolean[s.length()][s.length()];
            for(int i = 0; i < s.length(); i++){
                palindrome(s, i, i+1, dp);
                palindrome(s, i, i, dp);
            }
            return dp;
        }

        private void palindrome(String s, int left, int right, boolean[][] dp) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                dp[left--][right++] = true;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}