package com.ggx.datastructure.heap;

/**
 *  二叉堆是一种特殊的堆，二叉堆是完全二叉树或者是近似完全二叉树。
 *  二叉堆满足堆特性：父节点的键值总是保持固定的序关系于任何一个子节点的键值，且每个节点的左子树和右子树都是一个二叉堆。
 *  当父节点的键值总是大于或等于任何一个子节点的键值时为“最大堆”。当父节点的键值总是小于或等于任何一个子节点的键值时为“最小堆”。
 *  本类是最小堆的简单实现
 *  备注:根节点位置为1，所以i节点的子节点为2i和2i+1
 * @author ggx
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

    //插入元素
    public void insert(AnyType x){
        if(currentSize == array.length - 1){

//            enlargeArray(array.length*2 + 1);
        }
        int hole = ++currentSize;
        //当前元素小于父元素时，与父元素交换并继续向上比较
        for(;hole > 1 && x.compareTo(array[hole/2]) < 0; hole /= 2){
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    //删除最小元素
    public AnyType deleteMin(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        AnyType minItem = findMin();
        //将最小元素替换为最后一个元素
        array[1] = array[currentSize--];
        //根节点下潜
        percolateDown(1);
        return minItem;
    }

    //下潜
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
