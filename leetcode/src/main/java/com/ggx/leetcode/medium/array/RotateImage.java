package com.ggx.leetcode.medium.array;

/**
 * https://leetcode.com/problems/rotate-image/
 * 翻转镜像90度
 * 备注:
 * 需要在原地翻转镜像，这将意味着必须直接修改映像，不能再分配一个镜像的空间完成旋转
 * Example:
 *   Given input matrix =
 *   [
 *     [ 5, 1, 9,11],
 *     [ 2, 4, 8,10],
 *     [13, 3, 6, 7],
 *     [15,14,12,16]
 *   ],
 *
 *  rotate the input matrix in-place such that it becomes:
 *   [
 *     [15,13, 2, 5],
 *     [14, 3, 4, 1],
 *     [12, 6, 8, 9],
 *     [16, 7,10,11]
 *   ]
 */
public class RotateImage {


    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for(int i = 0; i < length/2; i++){
            for(int j = i; j < length-1-i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length-1-j][i];
                matrix[length-1-j][i] = matrix[length-1-i][length-1-j];
                matrix[length-1-i][length-1-j] = matrix[j][length-1-i];
                matrix[j][length-1-i] = temp;
            }
        }
    }

    /**
     * 讨论区优秀答案
     * 待推理
     */
    public void rotateB(int[][] matrix) {
        int s = 0, e = matrix.length - 1;
        while(s < e){
            int[] temp = matrix[s];
            matrix[s] = matrix[e];
            matrix[e] = temp;
            s++; e--;
        }

        for(int i = 0; i < matrix.length; i++){
            for(int j = i+1; j < matrix[i].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
