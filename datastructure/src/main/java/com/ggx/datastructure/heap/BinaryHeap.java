package com.ggx.datastructure.heap;

/**
 * @author ggx
 * @version 1.0
 * @date 8/29/2017
 */
public class BinaryHeap <AnyType extends Comparable<? super AnyType>>{

    private static final int DEFAULT_CAPACITY = 10; //默认堆大小

    private int currentSize; //当前堆大小

    private AnyType[] array; //数组

    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int size){
        this.currentSize = 0;
        array = (AnyType[])new Comparable[size + 1];
    }

    public BinaryHeap(AnyType[] item){
        currentSize = item.length  ;
        array = (AnyType[]) new Comparable[(currentSize +2) * 11/10];
        int i = 1;
        for(AnyType a : item){
            array[i++] = a;
        }
        buildHeap();
    }

    private void buildHeap() {

        for(int i = currentSize / 2; i > 0; i++){
            percolateDown(i);
        }
    }

    public void insert(AnyType x){
        if(currentSize == array.length - 1){
//            enlargeArray(array.length*2 + 1);
        }
        int hole = ++currentSize;
        for(;hole > 1 && x.compareTo(array[hole/2]) < 0; hole /= 2){
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    public AnyType deleteMin(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    private void percolateDown(int hole) {

        int child;
        AnyType tmp = array[hole];
        for(; hole * 2 <= currentSize; hole = child) {

            child = hole * 2;
            if(child != currentSize && array[child + 1].compareTo(array[child]) < 0){
                child++;
            }

            if (array[child].compareTo(tmp) < 0){
                array[hole] = array[child];
            }else{
                break;
            }
        }
        array[hole] = tmp;
    }


    private AnyType findMin() {

        if(isEmpty()) return null;
        return array[1];
    }


    private boolean isEmpty() {

        return currentSize == 0;
    }
}
