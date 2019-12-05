package com.ggx.leetcode.easy.dp;

import java.util.*;

public class BestOrderAmount {

    public static List bestOrderArray(int[] orders, int orderAmount){

        Map<Integer, List<Integer>> orderAmountMap = new HashMap<>();
        Arrays.sort(orders);
        orderAmountMap.put(0, new ArrayList<>());
        for(int i = 0; i < orders.length; i++){
            for(int j = orderAmount; j >= orders[i]; j--){
                if(orderAmountMap.containsKey(j)){
                    continue;
                }
                int prevIndex = j - orders[i];
                if(orderAmountMap.containsKey(prevIndex)){
                    List<Integer> list = new ArrayList<>(orderAmountMap.get(prevIndex));
                    list.add(orders[i]);
                    orderAmountMap.put(j, list);
                }
            }
        }
        return orderAmountMap.get(orderAmount);
    }

    public static void main(String[] args){
        int[] orders = {10, 15, 18, 40, 13, 30, 20, 25, 24, 23, 45, 35, 46, 48, 50};
        bestOrderArray(orders, 40);
    }
}
