package com.ggx.bytedance.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 并查集，有空看下
 */
public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        UnionFind uf = new UnionFind(nums);
        for(int v : nums){
            uf.union(v, v+1);
        }
        int max = 1;
        for(int v : nums){
            max = Math.max(max, uf.find(v) - v + 1);
        }
        return max;
    }
}

class UnionFind{
    private int count;
    private Map<Integer, Integer> parentMap;

    public UnionFind(int[] arr){
        count = arr.length;
        parentMap = new HashMap<>();
        for(int v : arr){
            parentMap.put(v, v);
        }
    }

    void union(int p, int q){
        Integer rootP = find(p), rootQ = find(q);
        if(rootP == rootQ) return;
        if(rootP == null || rootQ == null) return;
        parentMap.put(rootP, rootQ);
        count--;
    }

    Integer find(int p) {
        if(!parentMap.containsKey(p)){
            return null;
        }

        int root = p;
        while(root != parentMap.get(root)){
            root = parentMap.get(root);
        }

        while(p != parentMap.get(p)){
            int curr = p;
            p = parentMap.get(p);
            parentMap.put(curr, root);
        }
        return root;
    }

}
