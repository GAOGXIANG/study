package com.ggx.leetcode.easy.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    /**
     * 回溯算法，递归
     */
    private void backTrack(int[] candidates, int target, int index, ArrayList<Integer> temp, List<List<Integer>> result) {
        if(target == 0){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(target >= candidates[i]){
                temp.add(candidates[i]);
                backTrack(candidates, target - candidates[i], i, temp, result);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
