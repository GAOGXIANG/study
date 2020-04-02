package com.ggx.leetcode.medium.array;

/**
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/zero-matrix-lcci
 */
public class SetZeroes {

    public void setZeroes(int[][] matrix) {
        int[] zeroRow = new int[matrix.length];
        int[] zeroColumn = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 0){
                    zeroRow[i] = 1;
                    zeroColumn[j] = 1;
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(zeroRow[i] == 1 || zeroColumn[j] == 1)
                    matrix[i][j] = 0;
            }
        }
    }
}
