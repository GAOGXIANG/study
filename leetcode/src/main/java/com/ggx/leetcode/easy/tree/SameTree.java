package com.ggx.leetcode.easy.tree;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/same-tree/
 *
 * 100.相同的树
 * 深度优先遍历
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p != null && q != null){
            //如果两树根根节点都不为null，且值相等
            Stack<TreeNode> pStack = new Stack<>();
            TreeNode pCurrNode = p;
            Stack<TreeNode> qStack = new Stack<>();
            TreeNode qCurrNode = q;
            while(pCurrNode != null || qCurrNode != null || !pStack.isEmpty() || !qStack.isEmpty()){
                while(pCurrNode != null || qCurrNode != null){
                    if(pCurrNode == null || qCurrNode == null) return false;
                    if(pCurrNode.val == qCurrNode.val){
                        pStack.push(pCurrNode);
                        pStack.push(qCurrNode);
                        pCurrNode = pCurrNode.left;
                        qCurrNode = qCurrNode.left;
                    }else{
                        return false;
                    }
                }
                pCurrNode = pStack.pop();
                qCurrNode = qStack.pop();
                pCurrNode = pCurrNode.right;
                qCurrNode = qCurrNode.right;
                if(pCurrNode != null || qCurrNode != null){
                    if(pCurrNode == null || qCurrNode == null) return false;
                    if(pCurrNode.val != qCurrNode.val){
                        return false;
                    }
                }
            }
        }else{
            if(p != null || q!= null){
                return false;
            }
        }
        return true;
    }

    //递归方式
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTreeRecursive(p.right, q.right) &&
                isSameTreeRecursive(p.left, q.left);
    }
}