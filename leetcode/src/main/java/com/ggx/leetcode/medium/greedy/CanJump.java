package com.ggx.leetcode.medium.greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 链接：https://leetcode-cn.com/problems/jump-game
 */
public class CanJump {

    public static boolean canJump(int[] nums) {
        int i = 0, j = 0, next, max, length = nums.length;
        while(i < length){
            j = 0;
            max = 0;
            next = 0;
            while(j <= nums[i]){
                if(i+j+nums[i+j] > max){
                    max = i+j+nums[i+j];
                    if(max >= length) return true;
                    next = j;
                }
                j++;
            }
            if(next == 0){
                return false;
            }
            i += next;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));
    }
}
