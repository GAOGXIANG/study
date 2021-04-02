package com.ggx.leetcode.medium.string;

/**
 * 5.https://leetcode-cn.com/problems/longest-palindromic-substring/
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

    /**===============================================动态规划===================================================*/

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

    /**=================================================Manacher===============================================*/

    /**
     * 马拉车算法
     */
    public static String longestPalindromeM(String s){
        if(s == null || s.length() == 0){
            return "";
        }
        String newStr = preProcess(s, '#');
        int[] p = new int[newStr.length()];
        int maxRight = 0, center = 0, newStrLength = newStr.length();
        int start = 0, maxLen = 1;
        int left = 0, right = 0;
        for(int i = 0; i < newStrLength; i++){
            if(i < maxRight){
                int mirror = center*2 - i;
                p[i] = Math.min(maxRight - i, p[mirror]);
            }
            left = i - p[i] - 1;
            right = i + p[i] + 1;
            while(left >= 0 && right < newStrLength && newStr.charAt(left) == newStr.charAt(right)){
                p[i] = ++p[i];
                left--;
                right++;
            }
            if(p[i] + i > maxRight){
                maxRight = p[i] + i;
                center = i;
            }
            if(p[i] > maxLen){
                maxLen = p[i];
                start = (i - p[i])/2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 预处理字符串
     * boundaries在字符串中不能出现
     */
    private static String preProcess(String s, char boundaries) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            result.append(boundaries).append(s.charAt(i));
        }
        result.append(boundaries);
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "abcacbd";
        System.out.println(longestPalindromeM(s));
    }

}
