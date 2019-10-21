package com.ggx.leetcode.easy.string;

/**
 * https://leetcode.com/problems/reverse-string/
 * 旋转字符串
 * Example:
 *  Input: ["h","e","l","l","o"]
 *  Output: ["o","l","l","e","h"]
 */
public class ReverseString {

    public void reverseString(char[] s) {
        for(int i = 0; i < s.length/2; i++){
            char c = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = c;
        }
    }
}
