package com.ggx.leetcode.hard.array;

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
