package com.ggx.leetcode.easy.array;

/**
 * 27. https://leetcode-cn.com/problems/remove-element/submissions/
 * 数组中删除元素
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int position = 0;
        for(int i : nums){
            if(i != val){
                nums[position++] = i;
            }
        }
        return position;
    }
}
