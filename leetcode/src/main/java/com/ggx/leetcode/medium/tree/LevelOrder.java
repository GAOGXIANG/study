package com.ggx.leetcode.medium.tree;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root != null){
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int count;TreeNode temp;
            while(!queue.isEmpty()){
                List<Integer> levelList = new ArrayList<>();
                count = queue.size();
                while(count > 0){
                     temp = queue.poll();
                     levelList.add(temp.val);
                     if(temp.left != null) queue.add(temp.left);
                     if(temp.right != null) queue.add(temp.right);
                     count--;
                }
                result.add(levelList);
            }
        }
        return result;
    }
}
