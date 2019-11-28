package com.ggx.datastructure.sort;

/**
 * 归并排序
 * 递归法（Top-down）
 * 1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 4. 重复步骤3直到某一指针到达序列尾
 * 5. 将另一序列剩下的所有元素直接复制到合并序列尾
 * 迭代法（Bottom-up）
 * 1. 原理如下（假设序列共有n个元素）：
 * 2. 将序列每相邻两个数字进行归并操作，形成ceil(n/2)个序列，排序后每个序列包含两/一个元素
 * 3. 若此时序列数不是1个则将上述序列再次归并，形成ceil(n/4)个序列，每个序列包含四/三个元素
 * 4. 重复步骤2，直到所有元素排序完毕，即序列数为1
 */
public class MergeSort {

    //迭代法
    public static void mergeSort(int[] array){
        int[] orderedArr = new int[array.length];
        for(int i = 2; i < array.length * 2; i *=2){
            for(int j = 0; j < (array.length + i -1)/i; j++){
                int left = i*j;
                int mid = left + i/2 >= array.length ? array.length - 1 : (left + i/2);
                int right = i * (j+1) - 1 >= array.length ? array.length - 1 : (i * (j+1) - 1);
                int start = left, l=left, m = mid;
                //归并
                while(l < m && m <= right){
                    if(array[l] < array[m]){
                        orderedArr[start++] = array[l++];
                    }else{
                        orderedArr[start++] = array[m++];
                    }
                }
                while(l < mid){
                    orderedArr[start++] = array[l++];
                }
                while(m <= right){
                    orderedArr[start++] = array[m++];
                }
                System.arraycopy(array, left, orderedArr, right, right - left + 1);
            }
        }
    }

    //递归法
    public static void mergeSortRecursive(int[] array){
        int length = array.length;
        int[] result = new int[length];
        mergeSortRecursive(array, result, 0, length -1);
    }

    private static void mergeSortRecursive(int[] array, int[] result, int start, int end) {
        if(start > end){
            return;
        }
        int length = end - start, mid = (length >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSortRecursive(array, result, start1, end1);
        mergeSortRecursive(array, result, start2, end2);
        int k = start;
        while(start1 <= end1 && start2 <= end2){
            if(array[start1] < array[start2]){
                result[k++] = array[start1++];
            }else{
                result[k++] = array[start2++];
            }
        }
        while(start1 <= end1){
            result[k++] = array[start1++];
        }
        while(start2 <= end2){
            result[k++] = array[start2++];
        }
        for(k=start; k<= end; k++){
            array[k] = result[k];
        }
    }
}
