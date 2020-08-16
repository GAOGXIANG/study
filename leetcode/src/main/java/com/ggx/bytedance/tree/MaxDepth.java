package com.ggx.bytedance.tree;

import com.ggx.leetcode.easy.tree.TreeNode;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 */
public class MaxDepth {

    private int maxDepth;

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        maxDepth(root, 1);
        return maxDepth;
    }

    private void maxDepth(TreeNode node, int depth) {
        if(node.left == null && node.right == null){
            maxDepth = Math.max(depth, maxDepth);
            return;
        }
        if(node.left != null){
            maxDepth(node.left, depth+1);
        }
        if(node.right != null){
            maxDepth(node.right, depth+1);
        }
    }
}
