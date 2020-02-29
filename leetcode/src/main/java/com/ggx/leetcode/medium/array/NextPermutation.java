package com.ggx.leetcode.medium.array;

/**
 * https://leetcode-cn.com/problems/next-permutation/
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        //从队尾找非逆序的第一个元素的位置
        int position = nums.length -2;
        while(position >= 0 && nums[position] >= nums[position+1]){
            position--;
        }
        //如果position不小于0，则和刚刚大于本位置的数交换位置
        if(position >=0){
            for(int i=position+1;i <= nums.length; i++){
                if(i == nums.length || nums[position] >= nums[i]){
                    swap(nums,position, i-1);
                    break;
                }
            }
        }
        //将position后的子队列翻转
        int l = position + 1;
        int r = nums.length - 1;
        while(l<r){
            swap(nums, l++, r--);
        }
    }

    /**
     * 交换元素
     */
    private static void swap(int[] nums, int position, int i) {
        int temp = nums[position];
        nums[position] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5,1,1};
        nextPermutation(nums);
    }
}
