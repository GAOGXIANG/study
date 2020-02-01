package com.ggx.leetcode.medium.string;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        //空字符串时返回0
        if(s == null || s.length() == 0){
            return 0;
        }

        int maxSubstringLength = 0;
        StringBuilder substring = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            int position = substring.toString().indexOf(s.charAt(i));
            if(position == -1){
                substring.append(s.charAt(i));
            }else{
                maxSubstringLength = maxSubstringLength > substring.length() ? maxSubstringLength : substring.length();
                substring = new StringBuilder(substring.substring(position + 1));
                substring.append(s.charAt(i));
            }
        }
        return maxSubstringLength > substring.length() ? maxSubstringLength : substring.length();
    }

    public static void main(String[] args){
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
