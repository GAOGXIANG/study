package com.ggx.leetcode.easy.backtrack;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 17. 电话号码的字母组合
 * 组合？ 字符串"ab" "ba"不算重复
 */
public class LetterCombinations {

    public static  List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(!"".equals(digits)){
             backtrace(0, digits.toCharArray(), "", result);
        }
        return result;
    }

    private static void backtrace(int i, char[] digits, String s, List<String> result) {
        if(s.length() == digits.length){
            result.add(s);
            return;
        }
        char d = digits[i];
        String letters = letterMap[d - '0'];
        for(int j = 0; j < letters.length(); j++){
            backtrace(i+1, digits, s + letters.charAt(j), result);
        }
    }

      private static String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };
//    private static LinkedList<String> resList;
//    public static List<String> letterCombinations(String digits) {
//
//        resList = new LinkedList<String>();
//        if(!"".equals(digits))
//            backtrace(0, digits, "", resList);
//
//        return resList;
//    }
//
//    public static void backtrace(int i, String digits, String s, LinkedList<String> resList) {
//        if(s.length() == digits.length()) {
//            resList.addLast(s);
//            return;
//        }
//
//        char digit = digits.charAt(i);   // 获取第i为数字
//        String letters = letterMap[digit - '0'];  // 获取第i为数字对应的各个字母
//        for (int j = 0; j < letters.length(); j++) {
//            backtrace(i + 1, digits, s + letters.charAt(j), resList);
//        }
//    }

    public static void main(String[] args) {
        String digits = "33";
        List<String> result = letterCombinations(digits);
        System.out.println(result.size());
    }

}
