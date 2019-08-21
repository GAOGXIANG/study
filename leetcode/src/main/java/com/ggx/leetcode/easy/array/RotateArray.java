package com.ggx.leetcode.easy.array;

/**
 * https://leetcode.com/problems/rotate-array/
 * 对一个给定的数组，向右旋转k步，k是非负数
 * 举例:
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        if (nums != null && nums.length != 1){
            k %= nums.length;
            for (; k > 0; k--) {
                rotateRight(nums);
            }
        }
    }

    //右旋一次
    private void rotateRight(int[] nums) {
        int temp = nums[nums.length - 1];
        for(int i = nums.length - 1; i > 0; i--){
            nums[i] = nums[i - 1];
        }
        nums[0] = temp;
    }

    /**====================================Good Answer==========================*/
    public void rotateB(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
