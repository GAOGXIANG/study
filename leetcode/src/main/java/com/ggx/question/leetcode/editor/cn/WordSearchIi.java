package com.ggx.question.leetcode.editor.cn;

//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 336 👎 0

import java.util.ArrayList;
import java.util.List;

public class WordSearchIi{
    public static void main(String[] args) {
        Solution solution = new WordSearchIi().new Solution();
        
    }

 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for(String word : words){
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board.length; j++){
                    backtrace(board, result, word, 0, 0,  0);
                }
            }
        }
        return null;
    }

     /**
      * 回溯查找是否包含word单词
      */
     private void backtrace(char[][] board, List<String> result, String word, int position, int i, int j) {
         if(position == word.length()){
             result.add(word);
         }

     }
 }
//leetcode submit region end(Prohibit modification and deletion)

}