package com.ggx.bytedance.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 */
public class SolveNQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[][] position = new int[n][n];
        if(solveNQueens(0, 0, n, position)){
            for(int i = 0; i < position.length; i++){
                result.add(new ArrayList<>());
                for(int j = 0; j < position.length; j++) {
                    if (position[i][j] == 2){
                        result.get(i).add("Q");
                    }else{
                        result.get(i).add(".");
                    }
                }
            }
        }
        return result;
    }

    private static boolean solveNQueens(int row, int column, int n, int[][] position) {
        if(n == 0) return true;
        for(int i = row; i < position.length; i++){
            for(int j = column; j < position.length; j++){
                if(position[i][j] == 0){
                    position[i][j] = 2;
                    refreshPosition(i, j, position, 1);
                    if(solveNQueens(i, j, n-1, position)){
                        return true;
                    }
                    position[i][j] = 0;
                    refreshPosition(i, j, position, 0);
                }
            }
        }
        return false;
    }

    /**
     * 将位置i,j皇后影响的范围置为target值
     */
    private static void refreshPosition(int i, int j, int[][] position, int target) {

        int count = 1;
        while(count < 8){
            if(i - count >= 0) position[i-count][j] = target;
            if(i + count < position.length) position[i+count][j] = target;
            if(j - count >= 0) position[i][j-count] = target;
            if(j + count < position.length) position[i][j+count] = target;
            if(i-count >= 0 && j-count >= 0) position[i-count][j-count] = target;
            if(i+count < position.length && j+count < position.length) position[i+count][j+count] = target;
            count++;
        }
    }

    public static void main(String[] args) {
        List<List<String>> result = solveNQueens(4);
        System.out.println(result);
    }
}
