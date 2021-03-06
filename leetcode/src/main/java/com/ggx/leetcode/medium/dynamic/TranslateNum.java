package com.ggx.leetcode.medium.dynamic;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 */
public class TranslateNum {

    private int result;

    public int translateNum(int num) {
        if(num <= 9) return 1;
        int temp = num % 100;
        if(temp > 9 && temp < 26) { //可单可双
            return translateNum(num/10) + translateNum(num/100);
        }
        else return translateNum(num/10);
    }

}
