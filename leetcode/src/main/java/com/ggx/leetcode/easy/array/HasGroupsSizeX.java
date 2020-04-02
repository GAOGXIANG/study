package com.ggx.leetcode.easy.array;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
 * 给定一副牌，每张牌上都写着一个整数。
 * <p>
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * <p>
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 */
public class HasGroupsSizeX {

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> groupMap = new HashMap<>();
        for (int i : deck) {
            if (groupMap.containsKey(i)) {
                groupMap.put(i, groupMap.get(i) + 1);
            } else {
                groupMap.put(i, 1);
            }
        }
        int x = 0;
        for (int i : groupMap.values()) {
            x = gcd(x, i);
            if (x == 1) {
                return false;
            }
        }
        return true;
    }

    private int gcd(int x, int i) {
        return i == 0 ? x : gcd(i, x % i);
    }

    //用数组效率更高...
//    public boolean hasGroupsSizeX(int[] deck){
//        int[] count = new int[10000];
//        for (int c: deck)
//            count[c]++;
//
//        int g = -1;
//        for (int i = 0; i < 10000; ++i)
//            if (count[i] > 0) {
//                if (g == -1)
//                    g = count[i];
//                else
//                    g = gcd(g, count[i]);
//            }
//
//        return g >= 2;
//    }
//
//    public int gcd(int x, int y) {
//        return x == 0 ? y : gcd(y%x, x);
//    }

}
