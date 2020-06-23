package com.ggx.leetcode.hard.tree;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 */
public class MaxPathSum {

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return max;
    }

    private int maxPath(TreeNode node) {
        if(node == null) return 0;

        int leftMaxPath = Math.max(maxPath(node.left), 0);
        int rightMaxPath = Math.max(maxPath(node.right), 0);

        int priceNewPath = node.val + leftMaxPath + rightMaxPath;

        max = Math.max(priceNewPath, max);
        return node.val + Math.max(leftMaxPath, rightMaxPath);
    }
}
