package com.ggx.datastructure.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 1.从第一个元素开始，该元素可以认为已经被排序
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5.将新元素插入到该位置后
 * 6.重复步骤2~5
 */
public class InsertionSort {

    public static void insertionSort(int[] array){
        for(int i = 1; i < array.length; i++){
            int temp = array[i];
            int j = i;
            for(; j >= 1 && temp < array[j-1]; j--){
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
    }

    public static void main(String[] args){
        int[] array = {3,1,7,8,6,24,13,18,9,10};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
