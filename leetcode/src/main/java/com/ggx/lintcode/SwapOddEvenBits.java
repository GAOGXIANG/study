package com.ggx.lintcode;

/**
 * 交换奇偶二进制位
 * @author ggx
 * @version 1.0
 * @date 9/21/2017
 */
public class SwapOddEvenBits {

    /*
     * @param x: An integer
     * @return: An integer
     */
    public static int swapOddEvenBits(int x) {

        int even = x & 0xAAAAAAAA;
        int odd = x & 0x55555555;
        int result = (even >> 1) | (odd << 1);
        return result;
    }

    public static void main(String[] args){
        int result = swapOddEvenBits(5);
        System.out.println(result);
    }
}
