package com.ggx.bytedance.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出：2
 * 解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回 2 。
 * 示例 2：
 *
 * 输入：
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出：1
 * 解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
 *
 *
 * 提示：
 *
 * 1 <= N <= 200
 * M[i][i] == 1
 * M[i][j] == M[j][i]
 */
public class FindCircleNum {

    public int findCircleNum(int[][] M) {
        FriendshipUnion union = new FriendshipUnion();
        for(int i = 0; i < M.length; i++){
            for(int j = 0; j < M[i].length; j++){
                if(M[i][j] == 1){
                    union.union(i, j);
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == 1) {
                    set.add(union.find(i));
                }
            }
        }
        return set.size();
    }
}

class FriendshipUnion{

    private Map<Integer, Integer> friendMap;

    public FriendshipUnion(){
        friendMap = new HashMap<>();
    }

    void union(int i, int j){
        if(i == j){
            friendMap.put(i,j);
        }
        int rootI = find(i), rootJ = find(j);
        int root = Math.min(rootI, rootJ);
        friendMap.put(i, root);
        friendMap.put(j, root);
    }

    int find(int i) {
        if(!friendMap.containsKey(i)){
            return i;
        }

        int root = i;
        while(root != friendMap.get(root)){
            root  = friendMap.get(root);
        }

        while(i != friendMap.get(i)){
            int curr = i;
            i = friendMap.get(i);
            friendMap.put(curr, root);
        }
        return root;
    }
}