package com.ggx.leetcode.medium.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses
 * 22. 括号生成
 * Example:
 * n = 3：结果为:
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Parenthesis {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        int open = 0, close = 0;
        backTrack(result, open, close, new String(), n);
        return result;
    }

    /**
     * 回溯算法
     */
    private static void backTrack(List<String> result, int open, int close, String tmp, int n) {

        if(tmp.length() == n*2){
            result.add(tmp);
            return;
        }
        if(open < n){
            backTrack(result, open+1, close,tmp+"(", n);
        }
        if(open > close){
            backTrack(result, open, close+1, tmp+")", n);
        }
    }

    public static void main(String[] args){
        List<String> result = generateParenthesis(3);
        System.out.println(result);
    }
}
