package com.ggx.leetcode.hard.array;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Trap {

    public static int trap(int[] height) {
        int area = 0, l, r, h;
        for(int i = 0; i < height.length - 2; i++){
            if(height[i] > height[i+1]){
                r = 0;
                h = 0;
                for(int j = i+2; j < height.length; j++){
                    if(height[j] >= h){
                        r = j;
                        h=height[j];
                        if(height[j] >= height[i]) {
                            h=height[i];
                            break;
                        }
                    }
                }
                if(r != 0){
                    while((i+1) < r){
                        if(height[i+1] < h) {
                            area += h - height[i + 1];
                        }
                        i++;
                    }
                }
            }
        }
        return area;
    }
    public static void main(String[] args){
//        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//        int[] height = {4, 2, 3};
        int[] height = {5,4,1,2};
        System.out.println(trap(height));
    }
}
