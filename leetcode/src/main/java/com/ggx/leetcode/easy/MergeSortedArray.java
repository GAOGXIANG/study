package com.ggx.leetcode.easy;

public class MergeSortedArray {

    /**
     * 合并到nums1里面，需要从后边开始，从头开始需要数据copy，效率会低
     */
    public static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n){
       int k = m + n - 1;
       m--; n--;
       while(m > -1 && n > -1){
           if(nums1[m] > nums2[n]){
               nums1[k--] = nums1[m];
               m--;
           }else{
               nums1[k--] = nums2[n];
               n--;
           }
       }
       while(n > -1){
           nums1[k--] = nums2[n--];
       }
    }

    public static void main(String[] args){
        int[] m = new int[]{1,4,9,0,0,0};
        int[] n = new int[]{2,7,8};
        mergeSortedArray(m,3,n,3);
        for(int i : m){
            System.out.println(i);
        }
    }
}
