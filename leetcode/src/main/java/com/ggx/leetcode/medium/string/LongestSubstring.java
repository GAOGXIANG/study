package com.ggx.leetcode.medium.string;

import java.util.HashMap;
import java.util.Map;

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

//    //leetcode滑动窗口解法
//    public int lengthOfLongestSubstring(String s) {
//        int n = s.length(), ans = 0;
//        Map<Character, Integer> map = new HashMap<>(); // current index of character
//        // try to extend the range [i, j]
//        for (int j = 0, i = 0; j < n; j++) {
//            if (map.containsKey(s.charAt(j))) {
//                i = Math.max(map.get(s.charAt(j)), i);
//            }
//            ans = Math.max(ans, j - i + 1);
//            map.put(s.charAt(j), j + 1);
//        }
//        return ans;
//    }

}
