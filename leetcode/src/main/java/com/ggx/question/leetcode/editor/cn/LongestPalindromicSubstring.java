package com.ggx.question.leetcode.editor.cn;

//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2959 👎 0

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int start; //子串开始位置
        private int end; //子串结束位置

        public String longestPalindrome(String s) {
            char[] chars = s.toCharArray();
            for(int i = 0; i < s.length() - 1; i++){
                searchPalindrome(chars, i, i);
                searchPalindrome(chars, i, i+1);
            }
            return s.substring(this.start, this.end+1);
        }

        private void searchPalindrome(char[] s, int start, int end) {
            if(start >= 0 && end < s.length && s[start] == s[end]){
                if(this.end-this.start < end-start){
                    this.start = start;
                    this.end = end;
                }
                start--;
                end++;
                searchPalindrome(s, start, end);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}