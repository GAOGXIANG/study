package com.ggx.leetcode.easy.binarysearch;

public class Sqrt {

    //用二分查找获取平方根的整数位
    public static int mySqrtByBinarySearch(int x){
        if(x == 0){
            return 0;
        }
        int left = 1, right = x;
        //当mid^2 < x且(mid+1)^2 > x时，mid符合条件
        while(true){
            int mid = left + (right - left) / 2;
            if(mid > x/mid){
                right = mid - 1;
            }else{
                if((mid+1) > x / (mid+1)){
                    return mid;
                }
                left = mid + 1;
            }
        }
    }

    public static void main(String[] args){
        System.out.println(mySqrtByBinarySearch(10));
    }
}
