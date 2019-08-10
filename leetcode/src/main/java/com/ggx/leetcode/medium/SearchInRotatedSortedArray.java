package com.ggx.leetcode.medium;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 */
public class SearchInRotatedSortedArray {

    /**
     * 通过二分查找找到最小值的位置
     * 然后与两端比较确定在左右哪个半区二分查找
     */
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int minIndex = findMinIndex(nums);
        int left = 0, right = nums.length - 1;
        if(target > nums[right]) {
            right = minIndex-1;
        }else {
            left = minIndex;
        }
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) right = mid - 1;
            if(nums[mid] < target) left = mid + 1;
        }
        return -1;
    }

    /**
     * 查找最小值位置
     */
    private int findMinIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) /2;
            if(nums[mid] > nums[right]){
                //mid位置的值>右端的值，left右移
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
