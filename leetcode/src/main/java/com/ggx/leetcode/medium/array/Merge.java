package com.ggx.leetcode.medium.array;

import java.util.Arrays;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);
        int position = 0, length = intervals.length, j, i;
        while(position < length){
            j = position + 1;
            i = position + 1;
            while (j < length && intervals[position][1] >= intervals[j][0]){
                intervals[position][1] = Math.max(intervals[j][1],intervals[position][1]);
                j++;
            }
            while(i < j && j < length){
                intervals[i] = intervals[j];
                i++;
                j++;
            }
            length -= j - i;
            position++;
        }
        int[][] result = new int[position][2];
        System.arraycopy(intervals, 0, result, 0, length);
        return result;
    }

    /**
     * 快速排序
     * 将2维数组根据每个数组的第一个元素进行正序排序
     * @param intervals
     */
    private void quickSort(int[][] intervals, int head, int tail) {
        if (head >= tail || intervals == null || intervals.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = intervals[(head+tail)/2][0], value = intervals[(head+tail)/2][1];
        while(i < j){
            while(intervals[i][0] < pivot || (intervals[i][0] == pivot && intervals[i][1] < value)){
                i++;
            }
            while(intervals[j][0] > pivot || (intervals[j][0] == pivot && intervals[j][1] > value)){
                j--;
            }
            if(i < j) {
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

    public static void main(String[] args) {
        int[][] intervals = new int[2][2];
        intervals[0][0] = 1;
        intervals[0][1] = 4;
        intervals[1][0] = 1;
        intervals[1][1] = 5;
        Merge merge = new Merge();
        int[][] result = merge.merge(intervals);
        System.out.println(result);
    }

    //大神的简洁写法
//    public int[][] merge(int[][] intervals) {
//        // 先按照区间起始位置排序
//        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
//        // 遍历区间
//        int[][] res = new int[intervals.length][2];
//        int idx = -1;
//        for (int[] interval: intervals) {
//            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
//            // 则不合并，直接将当前区间加入结果数组。
//            if (idx == -1 || interval[0] > res[idx][1]) {
//                res[++idx] = interval;
//            } else {
//                // 反之将当前区间合并至结果数组的最后区间
//                res[idx][1] = Math.max(res[idx][1], interval[1]);
//            }
//        }
//        return Arrays.copyOf(res, idx + 1);
//    }
//
//    作者：sweetiee
//    链接：https://leetcode-cn.com/problems/merge-intervals/solution/chi-jing-ran-yi-yan-miao-dong-by-sweetiee/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
