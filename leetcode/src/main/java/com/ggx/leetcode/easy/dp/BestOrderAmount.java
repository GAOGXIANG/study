package com.ggx.leetcode.easy.dp;

import java.util.*;

public class BestOrderAmount {

    public static int[] bestOrderArray(int[] orders, int orderAmount){

        Map<Integer, List<Integer>> orderAmountMap = new HashMap<>();
        Arrays.sort(orders);
        for(int amount : orders){
            List<Integer> list = new ArrayList<>();
            list.add(amount);
            orderAmountMap.put(amount, list);
        }
        for(int i = 1; i < orders.length; i++){
            for(int j = orderAmount; j >= orders[i]; j--){
                if(orderAmountMap.containsKey(j)){
                    continue;
                }
                if(orderAmountMap.containsKey(j - orders[i])){
                    List<Integer> list = orderAmountMap.get(j - orders[i]);
                    list.add(orders[i]);
                    orderAmountMap.put(j, list);
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args){
        int[] orders = {10, 15, 18, 40, 13, 30, 20, 25, 24, 23, 45, 35, 46, 48, 50};
        bestOrderArray(orders, 40);
    }
}
