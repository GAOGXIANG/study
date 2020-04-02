package com.ggx.leetcode.medium.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/
 *
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 *
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 *
 * 示例 1:
 *
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 *
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 *
 */
public class MinIncrementForUnique {

    public static int minIncrementForUnique(int[] A) {
        int minIncrement = 0;
        Arrays.sort(A);
        for(int i = 0; i < A.length - 1; i++){
            if(A[i] >= A[i+1]){
                int newValue = A[i] + 1;
                minIncrement += newValue - A[i+1];
                A[i+1] = newValue;
            }
        }
        return minIncrement;
    }

    public static void main(String[] args){
//        int[] A = {3,2,1,2,1,7};
//        System.out.println(minIncrementForUnique(A));
        System.out.println(Long.MAX_VALUE );
    }
}
