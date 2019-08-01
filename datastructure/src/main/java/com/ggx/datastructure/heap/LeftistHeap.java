package com.ggx.datastructure.heap;

/**
 * @author ggx
 * @version 1.0
 * @date 9/3/2017
 */
public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {

    private Node<AnyType> root;

    private static class Node<AnyType>{

        AnyType element;
        Node<AnyType> left;
        Node<AnyType> right;
        int npl;  //null path length

        Node(AnyType element){

            this(element, null, null);
        }

        Node(AnyType element, Node<AnyType> lt, Node<AnyType> rt){
            element = element;
            left = lt;
            right = rt;
        }
    }

    public void merge(LeftistHeap<AnyType> rhs){
        if(this == rhs){
            return;
        }
        root = merge(root, rhs.root);
        rhs.root = null;
    }


    private Node<AnyType> merge(Node<AnyType> h1, Node<AnyType> h2) {

        if(h1 == null){
            return h2;
        }

        if(h2 == null){
            return h1;
        }

        if(h1.element.compareTo(h2.element) < 0){
            return merge1(h1, h2);
        }else{
            return merge1(h2, h1);
        }
    }

    private Node<AnyType> merge1(Node<AnyType> h1, Node<AnyType> h2) {

        if(h1.left == null){
            h1.left = h2;
        }else{
            h1.right = merge(h1.right, h2);
            if(h1.left.npl < h1.right.npl){
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private void swapChildren(Node<AnyType> h1) {

        Node<AnyType> tmp = h1.left;
        h1.left = h1.right;
        h1.right = tmp;
    }

    public void insert(AnyType x){
        root = merge(new Node<AnyType>(x), root);
    }

    public AnyType deleteMin() throws Exception {
        if(isEmpty()){
            throw new Exception("null == root");
        }
        AnyType minItem = root.element;
        root = merge(root.left, root.right);
        return minItem;
    }

    private boolean isEmpty() {
        return root == null;
    }
}
