package com.ggx.bytedance.tree;

import com.ggx.leetcode.easy.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 */
public class PathSum {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> curPath = new LinkedList<>();
        findPath(result, curPath, root, sum);
        return result;
    }

    private void findPath(List<List<Integer>> result, LinkedList<Integer> curPath, TreeNode node, int sum) {
        if(node == null) return;
        if(node.val > sum) return;
        curPath.add(node.val);
        if(node.val == sum && node.left == null && node.right == null){
            result.add(new ArrayList<>(curPath));
        }else{
            findPath(result, curPath, node.left, sum - node.val);
            findPath(result, curPath, node.right, sum - node.val);
        }
        curPath.removeLast();
    }
}
