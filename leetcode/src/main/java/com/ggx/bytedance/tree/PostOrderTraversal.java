package com.ggx.bytedance.tree;

import com.ggx.leetcode.easy.tree.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 */
public class PostOrderTraversal {

    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        Stack<TreeNode> helper = new Stack<TreeNode>();
        TreeNode current = root, r = null;  //r结点用来区分之前的结点是否被访问过
        while(current != null || !helper.isEmpty()){
            if(current != null){    //到树的最左面
                helper.push(current);
                current = current.left;
            }else{
                current = helper.peek();    //看最左结点有没有右子树
                if(current.right != null && current.right != r){
                    current = current.right;
                    helper.push(current);
                    current = current.left;     //右子树再到最左
                }else{
                    current = helper.pop();    //访问该结点，并标记被访问
                    ans.add(current.val);
                    r = current;
                    current = null;
                }
            }
        }
        return ans;
    }

    //https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode/
//    public List<Integer> postorderTraversal(TreeNode root) {
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        LinkedList<Integer> output = new LinkedList<>();
//        if (root == null) {
//            return output;
//        }
//
//        stack.add(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pollLast();
//            output.addFirst(node.val);
//            if (node.left != null) {
//                stack.add(node.left);
//            }
//            if (node.right != null) {
//                stack.add(node.right);
//            }
//        }
//        return output;
//    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        TreeNode left = new TreeNode(3);
        right.left = left;
        postOrderTraversal(root);
    }
}
