package com.ggx.leetcode.medium.tree;

import java.util.*;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root != null){
            List<TreeNode> currentLevel = new ArrayList<>();
            currentLevel.add(root);
            TreeNode treeNode;
            List<TreeNode> temp = new ArrayList<>();
            while(!currentLevel.isEmpty()){
                 for(int i = 0; i < currentLevel.size(); i++){
                     treeNode = currentLevel.get(i);
                     if(treeNode.left != null){
                         temp.add(treeNode.left);
                     }
                     if(treeNode.right != null){
                         temp.add(treeNode.right);
                     }
                     if(i == currentLevel.size() - 1){
                         result.add(treeNode.val);
                     }
                 }
                 currentLevel.clear();
                 currentLevel.addAll(temp);
                 temp.clear();
            }
        }
        return result;
    }

    //官方dfs思路
//    public List<Integer> rightSideView(TreeNode root) {
//        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
//        int max_depth = -1;
//
//        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
//        Stack<Integer> depthStack = new Stack<Integer>();
//        nodeStack.push(root);
//        depthStack.push(0);
//
//        while (!nodeStack.isEmpty()) {
//            TreeNode node = nodeStack.pop();
//            int depth = depthStack.pop();
//
//            if (node != null) {
//                // 维护二叉树的最大深度
//                max_depth = Math.max(max_depth, depth);
//
//                // 如果不存在对应深度的节点我们才插入
//                if (!rightmostValueAtDepth.containsKey(depth)) {
//                    rightmostValueAtDepth.put(depth, node.val);
//                }
//
//                nodeStack.push(node.left);
//                nodeStack.push(node.right);
//                depthStack.push(depth+1);
//                depthStack.push(depth+1);
//            }
//        }
//
//        List<Integer> rightView = new ArrayList<Integer>();
//        for (int depth = 0; depth <= max_depth; depth++) {
//            rightView.add(rightmostValueAtDepth.get(depth));
//        }
//
//        return rightView;
//    }

    //优秀题解  
    //    List<Integer> res = new ArrayList<>();
//
//    public List<Integer> rightSideView(TreeNode root) {
//        dfs(root, 0); // 从根节点开始访问，根节点深度是0
//        return res;
//    }
//
//    private void dfs(TreeNode root, int depth) {
//        if (root == null) {
//            return;
//        }
//        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
//        if (depth == res.size()) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
//            res.add(root.val);
//        }
//        depth++;
//        dfs(root.right, depth);
//        dfs(root.left, depth);
//    }
}
