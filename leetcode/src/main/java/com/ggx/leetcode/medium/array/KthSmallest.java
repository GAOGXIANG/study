package com.ggx.leetcode.medium.array;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 *  
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *  
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 */
public class KthSmallest {

    public int kthSmallest(int[][] matrix, int k) {
        MinHeap minHeap = new MinHeap(matrix.length*matrix.length +1);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                minHeap.add(matrix[i][j]);
            }
        }
        int result = 0;
        for(; k > 0; k--){
            result = minHeap.deleteMin();
        }
        return result;
    }

    class MinHeap{
        private int[] table;
        private int currentSize;

        MinHeap(int length){
            table = new int[length];
        }

        public void add(int v){
            int position = ++currentSize;
            table[position] = v;
            //如果小于父节点，与父节点替换
            int parent;
            while ((parent = position / 2) > 0 && table[position] < table[parent]) {
                swap(position , parent);
                position = parent;
            }
        }

        //交换节点
        private void swap(int position, int parent) {
            int temp = table[position];
            table[position] = table[parent];
            table[parent] = temp;
        }

        public int deleteMin(){
            int result = table[1];
            table[1] = table[currentSize--];
            percolateDown(1);
            return result;
        }

        /**
         * 下潜
         */
        private void percolateDown(int hole) {
            int child;
            int temp = table[hole];
            for(; hole * 2 <= currentSize; hole = child){
                child = hole*2;
                if(child != currentSize && table[child] > table[child + 1]){
                    child++;
                }
                if (table[child] < temp) {
                    table[hole] = table[child];
                }else{
                    break;
                }
            }
            table[hole] = temp;
        }
    }

    public static void main(String[] args){
        int[][] matrix = new int[3][3];
        matrix[0][0] = 1;
        matrix[0][1] = 3;
        matrix[0][2] = 5;
        matrix[1][0] = 6;
        matrix[1][1] = 7;
        matrix[1][2] = 12;
        matrix[2][0] = 11;
        matrix[2][1] = 14;
        matrix[2][2] = 14;
        KthSmallest kthSmallest = new KthSmallest();
        System.out.println(kthSmallest.kthSmallest(matrix, 4));
    }
}
