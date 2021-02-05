package com.ggx.question.leetcode.editor.cn;

//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matix[i][j] <= 109 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -109 <= target <= 109 
// 
// Related Topics 二分查找 分治算法 
// 👍 526 👎 0

public class SearchA2dMatrixIi{
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrixIi().new Solution();
        int[][] matrix = new int[2][1];
        matrix[0][0] = -1;
        matrix[1][0] = -1;
        solution.searchMatrix(matrix, -2);
    }

 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix == null || matrix.length == 0){
            return false;
        }
        boolean verticalFind, horizontalFind;
        int loopCount = Math.min(matrix.length, matrix[0].length);
        for(int i = 0; i < loopCount; i++){
            horizontalFind = binarySearch(matrix, i, target, false);
            verticalFind = binarySearch(matrix, i, target, true);
            if(horizontalFind || verticalFind){
                return true;
            }
        }
        return false;
    }

     private boolean binarySearch(int[][] matrix, int i, int target, boolean isVertical) {
        int l = i, r = isVertical ? matrix.length-1 : matrix[i].length-1, mid, midVal;
        while(l <= r){
            mid = l + ((r-l) >>> 1);
            midVal = isVertical ? matrix[mid][i] : matrix[i][mid];
            if(midVal == target){
                return true;
            }else if(midVal > target){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return false;
     }

//     public boolean searchMatrix(int[][] matrix, int target) {
//         // start our "pointer" in the bottom-left
//         int row = matrix.length-1;
//         int col = 0;
//
//         while (row >= 0 && col < matrix[0].length) {
//             if (matrix[row][col] > target) {
//                 row--;
//             } else if (matrix[row][col] < target) {
//                 col++;
//             } else { // found it
//                 return true;
//             }
//         }
//
//         return false;
//     }
 }
//leetcode submit region end(Prohibit modification and deletion)

}