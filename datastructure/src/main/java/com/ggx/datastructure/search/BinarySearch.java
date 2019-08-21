package com.ggx.datastructure.search;

/**
 * 二分查找
 * @author ggx
 * @version 1.0
 * @date 9/5/2017
 */
public class BinarySearch {

    /**
     * 实现模板1
     * @param key 需要查找的值
     * @param a 数组必须是有序的
     * @return
     */
    public static int search(int key, int[] a){
        int low = 0;
        int high = a.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >>> 1);
            if(key < a[mid]) {high = mid -1;}
            else if(key > a[mid]) {low = mid+1;}
            else {return mid;}
        }
        return -1;
    }

    /**
     * Template #2 is an advanced form of Binary Search.
     * It is used to search for an element or condition which requires accessing the current index
     * and its immediate right neighbor's index in the array.
     * 不是太明白比模板1高级在哪？
     */
    public static int search2(int[] nums, int target){
        //数组为null或者空数组时返回-1
        if(nums == null || nums.length == 0){
            return -1;
        }

        int left = 0, right = nums.length;
        //与模板1不同的是，右边界变中间值时不会减1
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {return mid;}
            else if(nums[mid] > target) {right = mid;}
            else {left = mid + 1;}
        }
        if(left != nums.length && nums[left] == target) return left;
        return -1;
    }

}
