package com.ggx.leetcode.medium.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/permutations/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
 * 求数组的全排列
 * Example:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(!result.isEmpty()){
                //插入新数的所有队列
                List<List<Integer>> tempResult = new ArrayList<>();
                for(int j = 0; j < result.size(); j++){
                    //取出以前的队列
                    List<Integer> temp = result.get(j);
                    //在0-temp.size位置插入新数
                    for(int k = 0; k <= temp.size(); k++){
                        List<Integer> list = new ArrayList<>(temp);
                        list.add(k, nums[i]);
                        tempResult.add(list);
                    }
                }
                result.clear();
                result.addAll(tempResult);
            }else{
                List<Integer> temp = new ArrayList<>(nums.length);
                temp.add(nums[i]);
                result.add(temp);
            }
        }
        return result;
    }

    /**================================================回溯算法===================================================*/

    public List<List<Integer>> permuteBetter(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

}
