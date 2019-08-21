package com.ggx.leetcode.easy.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 数组中的数字表示石头在当天的价格，设计一个方法计算最大利润
 * P.S:可以做多次交易，但是不能同时参加多次交易(不能同时持有多个石头)
 */
public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        int profit = 0; //默认利润为0
        //一个元素小于后一个元素时，差的值为利润
        for(int i = 0; i < prices.length; i++){
            if(prices.length - i != 1){
                if(prices[i] < prices[i+1]){
                    profit += prices[i+1] - prices[i];
                }
            }
        }
        return profit;
    }
}
