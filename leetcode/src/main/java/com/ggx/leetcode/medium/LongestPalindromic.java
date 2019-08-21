package com.ggx.leetcode.medium;

/**
 * 最大回环子字符串
 */
public class LongestPalindromic {

    public static String longestPalindrome(String s) {

        if(s == null || s.length() < 2){
            return s;
        }
        String palindromeStr = s.substring(0, 1);
        for(int i = 0; i < s.length() - 1; i++){
            for(int j = i+1; j < s.length(); j++){
                //相同时判断是否回环
                if(s.charAt(i) == s.charAt(j)){
                    String substring = s.substring(i, j+1);
                    if(substring.length() > palindromeStr.length() && judgeParlindrome(substring)){
                        palindromeStr = substring;
                    }
                }
            }
        }
        return palindromeStr;
    }

    //判断是否回环
    private static boolean judgeParlindrome(String substring) {

        for(int i = 0; i < substring.length()/2; i++){
            if(substring.charAt(i) != substring.charAt(substring.length() - i -1)){
                return false;
            }
        }
        return true;
    }

    /**===============================================牛逼的答案===================================================*/

    private int lo, maxLen;

    /**
     * 理论基础是动态规划
     */
    public String longestPalindromeF(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
}
