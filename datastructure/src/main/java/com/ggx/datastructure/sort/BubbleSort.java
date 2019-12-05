package com.ggx.datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 2.对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 * 3.针对所有的元素重复以上的步骤，除了最后一个。
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSort {

    public static void bubbleSort(int[] array){
        for(int i = 1; i < array.length; i++){
            for(int j = 0; j < array.length-i; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args){
        int[] array = {3,1,7,8,6,24,13,18,9,10};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
