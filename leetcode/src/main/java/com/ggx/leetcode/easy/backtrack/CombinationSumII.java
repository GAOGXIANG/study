package com.ggx.leetcode.easy.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 */
public class CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    /**
     * 回溯算法
     */
    private static void backTrack(int[] candidates, int target, int index, ArrayList<Integer> temp, List<List<Integer>> result) {

        if(target == 0){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i < candidates.length; i++){

            if(target < candidates[i]){
                break;
            }
            if(i > index && candidates[i] == candidates[i-1]){
                continue;
            }
            temp.add(candidates[i]);
            backTrack(candidates, target - candidates[i], i + 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args){
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> result = combinationSum2(candidates, 8);
        System.out.println(result.size());
    }
}
