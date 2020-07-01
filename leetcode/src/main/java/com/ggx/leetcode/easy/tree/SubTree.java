package com.ggx.leetcode.easy.tree;

import java.util.Stack;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 */
public class SubTree {


    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        if(t == null) return false;
        if(s != null) {
            Stack<TreeNode> treeNodeStack = new Stack<>();
            treeNodeStack.push(s);
            TreeNode temp;
            while(!treeNodeStack.isEmpty()){
                temp = treeNodeStack.pop();
                if(temp.val == t.val){
                    if(isSameTree(temp, t)){
                        return true;
                    }
                }
                if(temp.left != null){
                    treeNodeStack.push(temp.left);
                }
                if(temp.right != null){
                    treeNodeStack.push(temp.right);
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是子树
     */
    private boolean isSameTree(TreeNode temp, TreeNode t) {
        if(temp == null && t == null) return true;
        if(temp == null || t == null) return false;
        if(temp.val != t.val) return false;
        return isSameTree(temp.left, t.left) && isSameTree(temp.right, t.right);
    }

//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        if (t == null) return true;   // t 为 null 一定都是 true
//        if (s == null) return false;  // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false
//        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s,t);
//    }

}
