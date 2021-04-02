package com.ggx.datastructure.search;

/**
 * 字符串匹配的KMP算法
 */
public class KMP {

    public int strStr(String hayStack, String needle){
        if(hayStack == null || hayStack.length() == 0){
            return 0;
        }
        if(needle == null || needle.length() == 0){
            return -1;
        }
        int[] next = next(needle);
        int j = 0;
        for(int i = 0; i < hayStack.length(); i++){
            while(j > 0 && hayStack.charAt(i) != needle.charAt(j)){
                j = next[j-1]+1;
                if(i+needle.length()-j > hayStack.length()){
                    return -1;
                }
            }
            if(hayStack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if(j == needle.length()){
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    public int[] next(String needle){
        int[] next = new int[needle.length()];
        next[0] = -1;
        int j = -1;
        for(int i = 1; i < needle.length(); i++) {
            while (j != -1 && needle.charAt(j+1) != needle.charAt(i)) {
                j = next[j];
            }
            if(needle.charAt(j+1) == needle.charAt(i)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
