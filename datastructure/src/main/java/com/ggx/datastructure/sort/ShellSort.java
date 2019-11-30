package com.ggx.datastructure.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 算法效率O(n log2 n)
 *
 * 希尔排序的运行时间依赖于增量序列的选择，而证明很复杂【有兴趣可查看其他资料】。
 * 使用希尔增量时希尔排序最坏时间复杂度是：O(n^2)。
 * 使用Hibbard增量的希尔排序最坏时间复杂度是：O(n3/2)；最优时间复杂度是O(n5/4)。
 * 使用Sedgewick 增量序列,排序最坏时间复杂度是：O(n4/3)；平均时间复杂度是O(n7/6)。最好的序列是{1,5,19,41,109……}。该序列中的项或者是9 * 4i - 9  * 2i +1的形式，或者是4i - 3* 2i+1)的形式。
 * 空间复杂度：只用到一个临时变量，所以空间复杂度为O(1)；
 * 稳定性：不稳定排序。因为每一趟的步长不一样，所以步长长的插入排序可能会把后面的元素插入到前面。
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
