package com.ggx.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 *
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class SumNumbers {

//    //效率太低
//    public int sumNumbers(TreeNode root) {
//        //递归累加
//        if(root != null) {
//            List<Integer> leafList = new ArrayList<>();
//            addLeafNode(root, 0, leafList);
//            return leafList.stream().reduce(0, (a,b) -> a+b);
//        }
//        return 0;
//    }
//
//    /**
//     * 累加
//     * @param node  当前父节点
//     * @param i  前路径的值
//     * @param leafList   叶子队列
//     */
//    private void addLeafNode(TreeNode node, int i, List<Integer> leafList) {
//
//        int temp = node.val + i*10;
//        if(node.left == null && node.right == null){
//            leafList.add(temp);
//        }
//        if(node.left != null){
//            addLeafNode(node.left, temp, leafList);
//        }
//        if(node.right != null){
//            addLeafNode(node.right, temp, leafList);
//        }
//    }

}

