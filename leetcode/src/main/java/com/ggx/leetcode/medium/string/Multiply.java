package com.ggx.leetcode.medium.string;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Multiply {

    public static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int length1 = num1.length();
        int length2 = num2.length();
        int[] num1Array = new int[length1];
        int[] num2Array = new int[length2];
        for(int i  = 0; i < length1; i++){
            num1Array[i] = num1.charAt(length1 - i - 1) - '0';
        }
        for(int i  = 0; i < length2; i++){
            num2Array[i] = num2.charAt(length2 - i - 1) - '0';
        }
        int[] result = new int[length1 + length2 -1];
        for(int i = 0; i < length1; i++){
            for(int j = 0; j < length2; j++){
                result[i+j] += num1Array[i] * num2Array[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        int temp, quotient;
        for(int i = 0; i < result.length - 1; i++){
            temp = result[i];
            if(temp < 10){
                continue;
            }
            quotient = temp / 10;
            result[i] = temp - (quotient * 10);
            result[i+1] += quotient;
        }
        for(int i = result.length - 1; i >=0; i--){
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("999", "9999"));
    }
}
