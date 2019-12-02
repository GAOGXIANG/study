package com.ggx.datastructure.sort;

/**
 * 堆排序:
 * 若以升序排序说明，把数组转换成最大堆积(Max-Heap Heap)，这是一种满足最大堆积性质(Max-Heap Property)的二叉树：对于除了根之外的每个节点i, A[parent(i)] ≥ A[i]。
 * 重复从最大堆积取出数值最大的结点(把根结点和最后一个结点交换，把交换后的最后一个结点移出堆)，并让残余的堆积维持最大堆积性质。
 * 堆节点的性质：
 * 父节点i的左子节点的位置是2i+1
 * 父节点i的右子节点的位置是2i+2
 * 节点i的父节点的位置是:(i-1)/2
 * 特点:
 * 1、时间复杂度：O(nlogn)  2、空间复杂度：O(1)  3、非稳定排序  4、原地排序
 */
public class HeapSort {

    public static void heapSort(int[] array){
        int n = array.length;
        //最后一个节点父节点的位置是(n-2)/2
        //需要从后到前下潜构建最大堆
        for(int i = (n-2)/2; i >= 0; i--){
            percolateDown(array, i, n-1);
        }
        //删除最大堆的根，并与最后的位置交换
        for(int i = 0; i < n; i++){
            int temp = array[n-i-1];
            array[n-i-1] = array[0];
            array[0] = temp;
            percolateDown(array, 0, n-i-1);
        }
    }

    /**
     * 下潜
     */
    private static void percolateDown(int[] array, int i, int n) {
        int temp = array[i];
        int child;
        for(; i * 2 < n; i = child) {
            child = i * 2 + 1;
            //让child的值为最大子节点的位置
            if (child != n && array[child + 1] - array[child] > 0) {
                child++;
            }
            if (array[i] < array[child]) {
                array[i] = array[child];
            } else {
                break;
            }
        }
        array[i] = temp;
    }
}
