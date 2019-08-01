package com.ggx.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 树的遍历
 * @author ggx
 * @version 1.0
 * @date 8/7/2017
 */
public class TreeErgodic {


    /**=====================================树的前序遍历====================================*/
    /**
     * 递归实现
     * @param root
     * @return
     */
    public List preOrder(TreeNode root){
        List<Object> result = new ArrayList<Object>();
        if(root == null){
            return result;
        }

        preOrder(root, result);
        return result;
    }

    private void preOrder(TreeNode root, List<Object> result){
        if(root == null){
            return;
        }
        result.add(root);
        preOrder(root.getLeftChild(), result);
        preOrder(root.getRightChild(), result);
    }

    /**
     * 树的前序遍历-非递归实现
     * @param root
     * @return
     */
    public List preOrderTraversal(TreeNode root){
        List<Object> result = new ArrayList<Object>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        treeStack.push(root);
        while(!treeStack.empty()){
            TreeNode temp = treeStack.pop();
            result.add(temp.getData());
            if(temp.getRightChild() != null){
                treeStack.push(temp.getRightChild());
            }
            if(temp.getLeftChild() != null){
                treeStack.push(temp.getLeftChild());
            }
        }
        return result;
    }

    /**============================================中序遍历=============================================*/

    /**
     * 递归实现
     * @param root
     * @return
     */
    public List inOrder(TreeNode root){
        List<Object> result = new ArrayList<Object>();
        if(root == null){
            return result;
        }
        inOrder(root, result);
        return result;
    }

    private void inOrder(TreeNode root, List<Object> result){
        if(root == null){
            return;
        }
        inOrder(root.getLeftChild(), result);
        result.add(root.getData());
        inOrder(root.getRightChild(), result);
    }

    public List<Object> inOrderTraversal(TreeNode root){
        List<Object> result = new ArrayList<Object>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if(temp.getLeftChild() == null){
                TreeNode p = stack.pop();
                result.add(p.getData());
                if(p.getRightChild() != null){
                    stack.push(p);
                }
            }else{
                stack.push(temp.getLeftChild());
                temp.setLeftChild(null);
            }
        }
        return result;
    }

    /**====================================树的后序遍历====================================*/
    /**
     * 递归实现
     * @param root
     * @return
     */
    public List<Object> postOrder(TreeNode root){
        List<Object> result = new ArrayList<Object>();
        if(root == null){
            return result;
        }
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode root, List<Object> result){
        if(root == null){
            return;
        }
        postOrder(root.getLeftChild(), result);
        postOrder(root.getRightChild(), result);
        result.add(root.getData());
    }

    /**
     * 非遍历实现
     * @param root
     * @return
     */
    public List<Object> postOrderTraversal(TreeNode root){
        List<Object> result = new ArrayList<Object>();
        if(root == null){
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if(temp.getLeftChild() == null && temp.getRightChild() == null){
                stack.pop();
                result.add(temp.getData());
            }else{
                if(temp.getRightChild() != null){
                    stack.push(temp.getRightChild());
                    temp.setRightChild(null);
                }
                if(temp.getLeftChild() != null){
                    stack.push(temp.getLeftChild());
                    temp.setLeftChild(null);
                }
            }
        }
        return result;
    }

    public TreeNode maxNode(TreeNode root){
        TreeNode result = root;
        findMax(result, root);
        return result;
    }

    private void findMax(TreeNode result, TreeNode root) {
        
    }
}

class TreeNode{
    private Object data;
    private TreeNode leftChild;
    private TreeNode rightChild;

    private TreeNode(Object d){
        data = d;
    }

    private TreeNode(Object d, TreeNode leftChild, TreeNode rightChild){
        data = d;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}