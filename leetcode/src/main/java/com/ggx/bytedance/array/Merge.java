package com.ggx.bytedance.array;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        int[][] result = new int[1][2];
        quickSort(intervals, 0, intervals.length-1);
        mergeSort(intervals, 0, intervals.length - 1);
        return result;
    }

    /**
     * 归并排序
     */
    private void mergeSort(int[][] intervals, int head, int tail) {


    }

    /**
     * 快速排序
     */
    private void quickSort(int[][] intervals, int head, int tail) {
        if(head >= tail || intervals == null || intervals.length <= 1){
            return;
        }
        int i = head, j = tail, pivot = intervals[head+(tail-head)/2][0];
        while(i < j){
            while(intervals[i][0] <= pivot) i++;
            while(intervals[j][0] > pivot) j--;
            if(i < j){
                int[] temp = intervals[i];
                intervals[i] = intervals[j];
                intervals[j] = temp;
                i++;
                j--;
            }else if(i == j){
                i++;
            }
        }
        quickSort(intervals, head, j);
        quickSort(intervals, i, tail);
    }
}
