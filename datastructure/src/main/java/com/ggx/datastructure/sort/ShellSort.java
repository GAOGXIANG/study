package com.ggx.datastructure.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 算法效率O(n log2 n)
 */
public class ShellSort {

    public static void shellSort(int[] array){
        int length = array.length;
        int temp;
        for(int step = length/2; step >=1; step/=2){
            for(int i = step; i < length; i++){
                temp = array[i];
                int j = i;
                for(; j >= step && temp < array[j-step]; j-=step){
                    array[j] = array[j-step];
                }
                array[j] = temp;
            }
        }
    }

    public static void main(String[] args){
        int[] array = {3,1,7,8,6,24,13,18,9,10};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}
