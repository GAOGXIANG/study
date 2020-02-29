package com.ggx.leetcode.easy.greedy;

/**
 * https://leetcode-cn.com/problems/is-subsequence/
 * 392:判断子序列
 * 判断s是否是t的子序列
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 */
public class SubSequence {


    public static boolean isSubsequence(String s, String t) {
        //大量的charAt操作，提前转为char数组，测试证明速度快了5倍
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int position = 0, matchedNumber = 0;
        while(matchedNumber < sArray.length){
            boolean isMatched = false;
            for(; position < tArray.length; position++){
                if(tArray[position] == sArray[matchedNumber]){
                    position++;
                    isMatched = true;
                    matchedNumber++;
                    break;
                }
            }
            if(!isMatched) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s,t));
    }

    //最佳答案
//    public boolean isSubsequence(String s, String t) {
//        int index = -1;
//        for (char c : s.toCharArray()){
//            index = t.indexOf(c, index+1);
//            if (index == -1) return false;
//        }
//        return true;
//    }
}
