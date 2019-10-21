package com.ggx.leetcode.easy.string;

/**
 * https://leetcode.com/problems/reverse-integer/submissions/
 * 翻转数字
 * Example1:
 * Input: -123
 * Output: -321
 *
 * Example 3:
 * Input: 120
 * Output: 21
 * 备注:
 */
public class ReverseInteger {

    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE - x % 10) < res * 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }

        return sign * res;
    }
}
