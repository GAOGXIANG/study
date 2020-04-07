package com.ggx.leetcode.easy.string;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
 *
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 *
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 */
public class CountCharacters {

    public static int countCharacters(String[] words, String chars) {
        int result = 0;
        int[] charArr = new int[26];
        char[] charA = chars.toCharArray();
        int position = 0;
        for(int i = 0; i < charA.length; i++){
            position = charA[i] - 97;
            charArr[position] = charArr[position] + 1;
        }
        int[] tempArr;
        for(int i = 0; i < words.length; i++){
            tempArr = Arrays.copyOf(charArr, 26);
            charA = words[i].toCharArray();
            for(int j = 0; j < charA.length; j++){
                position = charA[j] - 97;
                tempArr[position] = tempArr[position]-1;
                if(tempArr[position] < 0){
                    break;
                }else{
                    if(j == charA.length - 1){
                        result += charA.length;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"cat","bt","hat","tree"};
        String chars = "atach";
        System.out.println(countCharacters(words, chars));
    }
}
