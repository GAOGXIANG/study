package com.ggx.leetcode.easy.dp;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int max = nums[0], sum;
        //动态规划，存储的是到当前位置子队列的最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            sum = dp[i-1] + nums[i];
            if(sum > nums[i]){
                dp[i] = sum;
            }else{
                dp[i] = nums[i];
            }
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }
}
