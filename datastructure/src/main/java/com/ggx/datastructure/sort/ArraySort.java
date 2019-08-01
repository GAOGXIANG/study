package com.ggx.datastructure.sort;

/**
 * 排序方法
 *
 * @author ggx
 * @date 2018-02-03
 */
public class ArraySort {

    /**
     * 插入排序
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        int j;
        for (int i = 1; i < a.length; i++) {
            AnyType tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 冒泡排序
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void bubbleSort(AnyType[] a) {

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j--) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    AnyType tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void chooseSort(AnyType[] a) {

        AnyType tmp = null;
        for (int i = 0; i < a.length; i++) {
            int k = i;
            for (int j = a.length; j > i; j++) {
                if (a[k].compareTo(a[j]) > 0) {
                    k = j;
                }
            }
            tmp = a[i];
            a[i] = a[k];
            a[k] = tmp;
        }
    }

    /**
     * ShellSort,using Shell's(poor) increments
     * 希尔排序
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {

        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    /**
     * 堆排序
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void heapSort(AnyType[] a) {
        //构建堆
        for (int i = a.length / 2; i >= 0; i--) {
            percDown(a, i, a.length);
        }

        for (int i = a.length - 1; i > 0; i--) {
            AnyType temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            percDown(a, i, i);
        }
    }

    /**
     * 构建堆和除去最大值
     *
     * @param a
     * @param i
     * @param length
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int length) {

        int child;
        AnyType tmp;
        for (tmp = a[i]; leftChild(i) < length; i = child) {
            child = leftChild(i);
            if (child != length - 1 && a[child].compareTo(a[child + 1]) < 0) {
                child++;
            }
            if (tmp.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }

    private static int leftChild(int i) {

        return 2 * i + 1;
    }


    /**
     * 归并排序
     *
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }


    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {

        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void
    merge(AnyType[] a, AnyType[] tmp, int leftPos, int rightPos, int rightEnd) {

        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //主循环
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmp[tmpPos++] = a[leftPos++];
            } else {
                tmp[tmpPos++] = a[rightPos++];
            }
        }
        //将两个分数组中剩余的部分放到结果中
        while(leftPos <= leftEnd){
            tmp[tmpPos++] = a[leftPos++];
        }

        while(rightPos <= rightEnd){
            tmp[tmpPos++] = a[rightPos++];
        }

        for(int i = 0; i < numElements; i++, rightEnd--){
            a[rightEnd] = tmp[rightEnd];
        }
    }


    /**
     * 快速排序
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a){
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 利用三数种植分割法获取枢纽元
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     * @return
     */
    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right){

        int center = (left + right) /2;
        if(a[center].compareTo(a[left]) < 0){
            swapReferences(a, left, center);
        }
        if(a[right].compareTo(a[left]) < 0){
            swapReferences(a, left, right);
        }
        if(a[right].compareTo(a[center]) < 0){
            swapReferences(a, center, right);
        }
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static final <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType[] a, int source, int target) {

        AnyType tmp = a[source];
        a[source] = a[target];
        a[target] = tmp;
    }

    /**
     * 快速排序递归方法
     * 用三数中指分割法和数组长度大于10
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int left, int right){

        if(left + 10 <= right){
            AnyType pivot = median3(a, left, right);

            //开始分割
            int i = left, j = right - 1;
            for(;;){
                while(a[++i].compareTo(pivot) < 0){}
                while(a[--j].compareTo(pivot) > 0){}
                if(i > j){
                    swapReferences(a, i, j);
                }else{
                    break;
                }
            }
            swapReferences(a, left, right - 1);
            quickSort(a, left, i - 1);
            quickSort(a, i, right);
        }else{
            insertionSort(a, left, right);
        }
    }

    /**
     * 普通插入排序外封装一层
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right){

        int j;
        for(int i = left + 1; i <= right; i++){
            AnyType tmp = a[i];
            for(j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--){
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }
}
