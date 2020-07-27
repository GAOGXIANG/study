package com.ggx.bytedance.string;

import java.util.Stack;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class ReverseWords {

    public static String reverseWords(String s) {
        char[] sCharArr = s.toCharArray();
        int l = 0;
        while(l < sCharArr.length && sCharArr[l] == ' '){
            l++;
        }
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = l; i <= sCharArr.length - 1; i++){
            if(sCharArr[i] == ' '){
                if(sb.length() > 0) {
                    stack.add(sb.toString());
                    sb = new StringBuilder();
                }
            }else{
                sb.append(sCharArr[i]);
            }
        }
        if(sb.length() > 0){
            stack.add(sb.toString());
            sb = new StringBuilder();
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    public static void main(String[] args) {
        String s = "  hello world!  ";
        System.out.println(reverseWords(s));
    }
}
