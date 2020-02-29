package com.ggx.leetcode.medium.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/3sum-closest/
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        int closestSum = Integer.MAX_VALUE/2, sum, left, right;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > target) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            left  = i + 1;
            right = nums.length - 1;
            while(left < right){
                sum = nums[i] + nums[left] + nums[right];
                if(sum == target) return sum;
                if(Math.abs(sum-target) < Math.abs(target-closestSum)) closestSum = sum;
                if(sum > target) right--;
                if(sum < target) left++;
            }
        }
        return closestSum;
    }

    public static void main(String[] args){
        int[] nums = {-1, 0,1, 1, 55};
        System.out.println(threeSumClosest(nums, -1));
    }
}
