package com.ggx.leetcode.easy.array;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * Examples:
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * It doesn't matter what values are set beyond the returned length.
 * 译:移除数组中重复元素
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int position = 0, currentValue = Integer.MIN_VALUE;
        for(int i : nums){
            if(i != currentValue){
                nums[position++] = i;
                currentValue = i;
            }
        }
        return position;
    }
}
