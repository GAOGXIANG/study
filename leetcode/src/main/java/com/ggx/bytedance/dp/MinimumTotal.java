package com.ggx.bytedance.dp;

import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class MinimumTotal {

    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        int history = 0;
        List<Integer> temp;
        for(int i = 0; i < triangle.size(); i++){
            temp = triangle.get(i);
            for(int j = 0; j < temp.size(); j++){
                if(i == 0){
                    dp[j] = temp.get(i);
                }else{
                    int sum = temp.get(j);
                    if(j == 0) {
                        sum = dp[j] + sum;
                    }else if(j == temp.size() - 1){
                        sum = history + sum;
                    }else{
                        sum = Math.min(history, dp[j]) + sum;
                    }
                    history = dp[j];
                    dp[j] = sum;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i : dp){
            min = Math.min(i, min);
        }
        return min;
    }
}
