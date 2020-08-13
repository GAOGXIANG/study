package com.ggx.bytedance.array;


import java.util.HashMap;
import java.util.Map;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class GetPermutation {

    public static String getPermutation(int n, int k) {
        //先确定首位
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> usedMap = new HashMap<>();
        appendNextNumber(n, k, n, usedMap, sb);
        return sb.toString();
    }

    /**
     * 拼接下一个数字
     */
    private static void appendNextNumber(int n, int k, int remain, Map<Integer, Integer> usedMap, StringBuilder sb) {
        if(remain <= 0) return;
        if(k == 0){
            while(remain > 0){
                if(!usedMap.containsKey(n)){
                    sb.append(n);
                    remain--;
                }
                n--;
            }
            return;
        }
        int number = 1;
        while (usedMap.containsKey(number)) {
            number++;
        }
        int arrayCount = 1;
        for(int i = 1; i < remain; i++){
            arrayCount *= i;
        }
        int quotient = k / arrayCount;
        int remainder = k - quotient*arrayCount;
        if(remainder == 0){
            quotient -= 1;
        }
        while(quotient > 0){
            number++;
            if(!usedMap.containsKey(number)){
                quotient--;
            }
        }
        usedMap.put(number, 1);
        sb.append(number);
        appendNextNumber(n, remainder, remain-1, usedMap, sb);
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3));
    }
}
