package com.ggx.datastructure.heap;

/**
 * @author ggx
 * @version 1.0
 * @date 9/5/2017
 */
public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_TREES = 1; //数组的默认大小

    private BinNode<AnyType>[] trees; //存放二项树的数组

    private int currentSize; //数组内的元素个数

    public BinomialQueue(){
        trees = new BinNode[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(AnyType x){

        currentSize = 1;
        trees = new BinNode[DEFAULT_TREES];
        trees[0] = new BinNode<AnyType>(x);
    }

    private void makeEmpty() {

        this.currentSize = 0;
        for(BinNode node : trees){
            node = null;
        }
    }


    private static class BinNode<AnyType>{

        AnyType element;
        BinNode<AnyType> leftChild;
        BinNode<AnyType> rightChild;

        BinNode(AnyType element){
           this(element, null, null);
        }

        BinNode(AnyType element, BinNode<AnyType> leftChild, BinNode<AnyType> rightChild){
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

    }
}
