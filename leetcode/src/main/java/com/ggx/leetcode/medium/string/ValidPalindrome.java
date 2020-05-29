package com.ggx.leetcode.medium.string;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 */
public class ValidPalindrome {

    public static boolean validPalindrome(String s) {
        if(s.length() == 0) return true;
        int l = 0, r = s.length()-1, changeNum = 1;
        char[] chars = s.toCharArray();
        boolean isValidPalindrome = validPalindrome(chars, l, r, changeNum);
        return isValidPalindrome;
    }

    public static boolean validPalindrome(char[] chars, int l, int r, int changeNum){
        while(l <= r){
            if(chars[l] == chars[r]){
                l++;
                r--;
            }else if(changeNum > 0){
                boolean isValidPalindrome = false;
                if(chars[l+1] == chars[r]){
                    isValidPalindrome = validPalindrome(chars, l+1, r, --changeNum);
                }
                if(chars[r-1] == chars[l]){
                    isValidPalindrome = validPalindrome(chars, l, r-1, --changeNum) || isValidPalindrome;
                }
                return isValidPalindrome;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "eeccccbebaeeabebccceea";
        System.out.println(validPalindrome(s));
    }
}
