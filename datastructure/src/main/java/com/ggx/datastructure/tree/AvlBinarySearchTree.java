package com.ggx.datastructure.tree;

/**
 * 平衡二叉树
 *
 * @author ggx
 * @version 1.0
 * @date 8/13/2017
 */
public class AvlBinarySearchTree<AnyType extends Comparable<? super AnyType>> {


    /**===========================================二叉平衡树==========================================*/

    /**
     * 平衡二叉树节点类
     *
     * @param <AnyType>
     */
    private static class AvlNode<AnyType> {

        AnyType element;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;

        AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
            height = 0;
        }

    }

    /**
     * 二叉平衡树的插入
     * 插入时会旋转以保持平衡
     * @param x
     * @param t
     * @return
     */
    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t){
        if(t == null){
            return new AvlNode<AnyType>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0){
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if(x.compareTo(t.element) < 0){
                    t = rotateWithLeftChild(t);
                }else{
                    t = doubleWithLeftChild(t);
                }
            }
        }else if(compareResult > 0){
            t.right = insert(x, t.right);
            if(height(t.right) - height(t.left) == 2){
                if (x.compareTo(t.element) > 0) {
                    t = rotateWithRightChild(t);
                }else{
                    t = doubleWithRightChild(t);
                }
            }
        }

        t.height = Math.max(height(t.left),height(t.right)) + 1;
        return t;
    }

    /**
     * 右旋
     * @param k2
     * @return
     */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2){
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right))+1;
        k1.height = Math.max(height(k1.left), k2.height)+1;
        return k1;
    }

    /**
     * 左旋
     * @param k2
     * @return
     */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2){

        AvlNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        return k1;
    }

    /**
     * 左右旋
     * @param k3
     * @return
     */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {

        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 右左旋
     * @param k3
     * @return
     */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3){
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }
}
