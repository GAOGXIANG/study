package com.ggx.leetcode.medium.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * 判断一个9*9的数独是否有效，只有已填充的字段需要效验以下规则:
 * 1. 每一行不能有重复的1-9数字
 * 2. 每一列不能有重复的1-9数字
 * 3. 9个3*3的子网格不能有重复的1-9数字
 * 备注:
 * 1. 数独板（部分填充的）必须有效但不一定是有解的
 * 2. 只有填充的格子要验证是否符合上述规则
 * 3. 本题的数独只包含数字1-9和字符"."
 * 4. 数独板的大小总是9*9
 */
public class ValidSudoku {

    /**
     * 讨论区优秀答案
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> sudokuSet = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                char value = board[i][j];
                if('.' != value){
                    String rowStr = i + "(" + value + ")";
                    String columnStr = "(" + value +")" + j;
                    String rowColStr = (i/3 + 1) + "(" + value + ")" + (j/3 + 1);
                    if(!(sudokuSet.add(rowStr) && sudokuSet.add(columnStr) && sudokuSet.add(rowColStr))){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
