package com.ggx.leetcode.medium.search;

import java.math.BigDecimal;

public class Sqrt {

    public static BigDecimal sqrt(double d){
        BigDecimal res = new BigDecimal(d);
        BigDecimal l = BigDecimal.ZERO;
        BigDecimal r = BigDecimal.valueOf(d);
        BigDecimal two = BigDecimal.valueOf(2);
        BigDecimal indent = BigDecimal.valueOf(0.001D);
        BigDecimal mid = BigDecimal.ZERO, tempProduct, next;
        while(l.compareTo(r) <= 0){
            mid = l.add(r.subtract(l).divide(two, 3, BigDecimal.ROUND_HALF_UP));
            tempProduct = mid.pow(2);
            if(tempProduct.compareTo(res) > 0){
                r = mid.subtract(indent);
            }else{
                next = l.add(indent);
                if(next.pow(2).compareTo(res) > 0){
                    return mid;
                }
                l = next;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(6.25D).toPlainString());
    }
}
