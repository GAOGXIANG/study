package com.ggx.leetcode.easy.array;

/**
 * https://leetcode.com/problems/move-zeroes/
 * 将0移动到数组的后端
 * Example：
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        int zeros = 0; //已遍历0的个数
        for(int i = 0; i < nums.length; i++){
            //如果不为0，与第一位0交换位置
            if(nums[i] != 0){
                if(zeros != 0){
                    nums[i - zeros] = nums[i];
                    nums[i] = 0;
                }
            }else{
                //0的个数+1
                zeros++;
            }
        }
    }
}
