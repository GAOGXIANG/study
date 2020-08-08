package com.ggx.datastructure.sort;

/**
 * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为较小和较大的2个子序列，然后递归地排序两个子序列。
 *
 * 步骤为：
 *
 * 挑选基准值：从数列中挑出一个元素，称为“基准”（pivot），
 * 分割：重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（与基准值相等的数可以到任何一边）。在这个分割结束之后，对基准值的排序就已经完成，
 * 递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。
 * 递归到最底部的判断条件是数列的大小是零或一，此时该数列显然已经有序。
 *
 * 选取基准值有数种具体方法，此选取方法对排序的时间性能有决定性影响。
 *
 * 时间复杂度:O(nlogn)
 */
public class QuickSort {

    public static void quickSort(int[] array, int head, int tail) {
        if (head >= tail || array == null || array.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = array[(head + tail) / 2];
        while (i <= j) {
            while (array[i] < pivot) {
                ++i;
            }
            while (array[j] > pivot) {
                --j;
            }
            if (i < j) {
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        quickSort(array, head, j);
        quickSort(array, i, tail);
    }
}
