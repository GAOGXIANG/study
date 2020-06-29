package com.ggx.leetcode.easy.tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *  
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 */
public class DiameterOfBinaryTree {

    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {

        if(root == null) return 0;
        diameter(root);
        return diameter;
    }

    private int diameter(TreeNode node) {
        if(node == null) return -1;
        int leftDepth = diameter(node.left) + 1;
        int rightDepth = diameter(node.right) + 1;
        int length = leftDepth + rightDepth;
        diameter = Math.max(length, diameter);
        return Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(treeNode));
    }
}
