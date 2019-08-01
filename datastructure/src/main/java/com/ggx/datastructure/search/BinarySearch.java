package com.ggx.datastructure.search;

/**
 * 二分查找
 * @author ggx
 * @version 1.0
 * @date 9/5/2017
 */
public class BinarySearch {

    /**
     *
     * @param key
     * @param a 数组必须是有序的
     * @return
     */
    public static int search(int key, int[] a){
        int low = 0;
        int high = a.length - 1;
        while(low <= high){
            int mid = low + (high - low) >>> 1;
            if(key < a[mid]) high = mid -1;
            if(key > a[mid]) low = mid+1;
            if(key ==  a[mid]) return mid;
        }
        return -1;
    }
}
