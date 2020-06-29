package com.ggx.leetcode.medium.dynamic;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        int length = coins.length;
        //如果能被最大面值整除，直接返回
        if(amount % coins[length - 1] == 0){
            return amount/coins[length - 1];
        }
        //初始化用最大面值的dp
        int val = coins[length - 1];
        for(int i = val; i <= amount; i=i+val){
            dp[i] = dp[i-val] + 1;
        }
        //面值从大到小使用
        for(int i = length - 2; i >=0; i--){
            val = coins[i];
            for(int j = val; j <= amount; j++){
                if(j-val ==0){
                    dp[j] = 1;
                }else if(dp[j-val] != 0){
                    if(dp[j] == 0){
                        dp[j] = dp[j-val] + 1;
                    }else {
                        dp[j] = Math.min(dp[j - val] + 1, dp[j]);
                    }
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
