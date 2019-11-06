package com.ggx.leetcode.easy.string;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * 寻找第一个不重复的字母，并且返回它的位置，不存在返回-1
 * 备注：假设所有的字母都是小写的
 */
public class FirstUniqueCharacterInString {

    //网上的优秀答案
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
