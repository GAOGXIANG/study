package com.ggx.leetcode.easy.array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/single-number/
 * 一个数组，只有一个元素是一个，其他的都是两个。找出这个唯一的元素
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        Set<Integer> result = new HashSet<>();
        for(int i : nums){
            if(!result.add(i)){
                result.remove(i);
            }
        }
        return result.iterator().next();
    }

    /**
     * 优秀的方法：用异或实现
     * 1、异或满足交互率，2、任意数与0异或等于本身，3.任意数与自身异或值为0
     * 2^1^3^1^3 = 1^1^3^3^2 = 0^0^2 = 2
     */
    int singleNumberBest(int A[], int n) {
        int result = 0;
        for (int i = 0; i<n; i++)
        {
            result ^=A[i];
        }
        return result;
    }
}
