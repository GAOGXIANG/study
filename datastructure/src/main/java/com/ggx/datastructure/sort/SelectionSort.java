package com.ggx.datastructure.sort;

/**
 * 选择排序
 * 思想:首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置(如果第一个元素就是最小元素那么它就和自己交换)。
 * 其次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。
 * 如此往复，直到将整个数组排序。这种方法我们称之为选择排序。
 */
public class SelectionSort {

    public static void selectionSort(int[] array){
        for(int i = 0; i < array.length; i++){
            int minPosition = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[minPosition] > array[j]){
                    minPosition = j;
                }
            }
            //如果最小位置变化，更换地址
            if(minPosition != i){
                int min = array[minPosition];
                array[minPosition] = array[i];
                array[i] = min;
            }
        }
    }
}
