package com.ggx.leetcode.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * 从数组中寻找两个数和为target
 * 可以假设只有一个正确答案，而且一个元素不能使用两次
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for(int i = 0; i < nums.length; i++){
            int minus = target - nums[i];
            if (map.containsKey(minus)) {
                result[0] = map.get(minus);
                result[1] = i;
                break;
            }else{
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
