package com.ggx.leetcode.medium.dynamic;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 */
public class FindLength {

    //动态规划解法
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];
        int maxSubLength = 0;
        for(int i = A.length - 1; i >= 0; i--){
            for(int j = B.length - 1; j >= 0; j--){
                if(A[i] == B[j]){
                    if(i + 1 >= A.length || j + 1 >= B.length){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i+1][j+1] + 1;
                        maxSubLength = Math.max(maxSubLength, dp[i][j]);
                    }
                }
            }
        }
        return maxSubLength;
    }
}
