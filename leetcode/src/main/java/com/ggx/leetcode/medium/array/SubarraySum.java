package com.ggx.leetcode.medium.array;


import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 */
public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int[] sumArray = new int[nums.length];
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(i == 0){
                    if(j == 0){
                        sumArray[j] = nums[i];
                    }else{
                        sumArray[j] = sumArray[j-1] + nums[j];
                    }
                }else{
                    sumArray[j] = sumArray[j] - nums[i-1];
                }
                if(sumArray[j] == k){
                    result++;
                }
            }
        }
        return result;
    }

    // 优化后的方法
    //链接：https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
//    public int subarraySum(int[] nums, int k) {
//        int count = 0, pre = 0;
//        HashMap< Integer, Integer > mp = new HashMap < > ();
//        mp.put(0, 1);
//        for (int i = 0; i < nums.length; i++) {
//            pre += nums[i];
//            if (mp.containsKey(pre - k))
//                count += mp.get(pre - k);
//            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
//        }
//        return count;
//    }
}
