package com.ggx.leetcode.easy.array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 * 判断数组是否包含重复元素
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> integers = new HashSet<>();
        for(int i : nums){
            if(!integers.add(i)){
                return true;
            }
        }
        return false;
    }
}
