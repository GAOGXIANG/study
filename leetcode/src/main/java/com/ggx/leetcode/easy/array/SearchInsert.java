package com.ggx.leetcode.easy.array;

/**
 * https://leetcode-cn.com/problems/search-insert-position/
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * ps:考察数组和二分查找
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length, mid;
        while(left < right){
            mid = left + ((right - left) >>> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        if(left != nums.length && nums[left] < target){
            return left + 1;
        }
        return left;
    }
}
