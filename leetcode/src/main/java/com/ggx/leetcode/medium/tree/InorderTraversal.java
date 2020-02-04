package com.ggx.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  94.https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 树的中序遍历
 * 尝试用非递归的方式实现
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root != null){
            inorderTraversal(root, result);
        }
        return result;
    }

    private void inorderTraversal(TreeNode node, List<Integer> result){
        if(node.left != null){
            inorderTraversal(node.left, result);
        }
        result.add(node.val);
        if(node.right != null){
            inorderTraversal(node.right, result);
        }
    }

    /**
     * 中序遍历非递归实现
     */
    private static List<Integer> inorderTraversalWithNonRecursive(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }

}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}