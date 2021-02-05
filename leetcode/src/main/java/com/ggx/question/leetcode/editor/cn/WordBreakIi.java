package com.ggx.question.leetcode.editor.cn;

//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 动态规划 回溯算法 
// 👍 408 👎 0

import java.util.*;
import java.util.stream.Collectors;

public class WordBreakIi{
    public static void main(String[] args) {
        Solution solution = new WordBreakIi().new Solution();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        solution.wordBreak(s, wordDict);
    }

 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        //超时解法
//    public List<String> wordBreak(String s, List<String> wordDict) {
//        List<String> result = new ArrayList<>();
//        Map<Integer, List<Integer>>initialDpMap = initialMatching(s, new  HashSet<>(wordDict));
//        backtraceWordBreak(s, result, 0, initialDpMap, new ArrayList<String>());
//        return result;
//    }
//
//     //初始化匹配
//     private Map<Integer, List<Integer>> initialMatching(String s, HashSet<String> wordDict) {
//        Map<Integer, List<Integer>> initialDpMap = new HashMap<>();
//        int maxDictLength = 0, end;
//        for(String word : wordDict){
//            maxDictLength = Math.max(maxDictLength, word.length());
//        }
//        for(int i = 0; i < s.length(); i++){
//            end = Math.min(i + maxDictLength + 1, s.length());
//            List<Integer> temp = null;
//            for(int j = i+1; j <= end; j++){
//                if(wordDict.contains(s.substring(i, j))){
//                    if(temp == null){
//                       temp = new ArrayList<>();
//                       initialDpMap.put(i, temp);
//                    }
//                    temp.add(j);
//                }
//            }
//        }
//        return initialDpMap;
//     }
//
//     private void backtraceWordBreak(String s, List<String> result, int start, Map<Integer, List<Integer>> initialDpMap, List<String> temp) {
//        if(start == s.length()){
//            result.add(temp.stream().collect(Collectors.joining(" ")));
//        }
//        List<Integer> startMatchList = initialDpMap.get(start);
//        if(startMatchList != null){
//            for(int i : startMatchList){
//                temp.add(s.substring(start, i));
//                backtraceWordBreak(s, result, i, initialDpMap, temp);
//                temp.remove(temp.size()-1);
//            }
//        }
//     }

     public List<String> wordBreak(String s, List<String> wordDict) {
         Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();
         List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);
         List<String> breakList = new LinkedList<String>();
         for (List<String> wordBreak : wordBreaks) {
             breakList.add(String.join(" ", wordBreak));
         }
         return breakList;
     }

     public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
         if (!map.containsKey(index)) {
             List<List<String>> wordBreaks = new LinkedList<List<String>>();
             if (index == length) {
                 wordBreaks.add(new LinkedList<String>());
             }
             for (int i = index + 1; i <= length; i++) {
                 String word = s.substring(index, i);
                 if (wordSet.contains(word)) {
                     List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                     for (List<String> nextWordBreak : nextWordBreaks) {
                         LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                         wordBreak.offerFirst(word);
                         wordBreaks.add(wordBreak);
                     }
                 }
             }
             map.put(index, wordBreaks);
         }
         return map.get(index);
     }

 }
//leetcode submit region end(Prohibit modification and deletion)

}