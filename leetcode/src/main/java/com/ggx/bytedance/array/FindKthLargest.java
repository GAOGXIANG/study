package com.ggx.bytedance.array;

import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class FindKthLargest {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int l, int r, int index) {
        int q = partition(nums, l, r);
        if(q == index){
            return nums[q];
        }else{
            return q < index ? quickSelect(nums, q+1, r, index) : quickSelect(nums, l, q-1, index);
        }
    }


    private int partition(int[] nums, int l, int r) {
        int ran = random.nextInt(r-l+1) + l;
        swap(nums, ran, r);
        int x = nums[r], j = l-1;
        for(int i = l; i < r; i++){
            if(nums[i] <= x){
                swap(nums, ++j, i);
            }
        }
        swap(nums, j+1, r);
        return j+1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
