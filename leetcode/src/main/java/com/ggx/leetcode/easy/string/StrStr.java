package com.ggx.leetcode.easy.string;

/**
 * 28.https://leetcode-cn.com/problems/implement-strstr/
 * 实现string的indexOf功能
 */
public class StrStr {

    public static int strStr(String haystack, String needle) {

        if(needle == null || needle.length() == 0){
            return 0;
        }
        for(int i = 0; i < haystack.length(); i++){
            if(haystack.charAt(i) == needle.charAt(0)) {
                for (int j = 0; j < needle.length()/2; j++){
                    if(i+j < haystack.length() && needle.charAt(j) == haystack.charAt(i+j)
                            && needle.charAt(needle.length()-j-1) == haystack.charAt(needle.length()-j-1+i)){
                        if(j == needle.length() - 1) {
                            return i;
                        }
                        continue;
                    }else{
                        break;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        System.out.println(strStr("a", ""));;
    }
}
