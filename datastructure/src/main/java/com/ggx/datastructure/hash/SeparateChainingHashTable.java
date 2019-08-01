package com.ggx.datastructure.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ggx
 * @version 1.0
 * @date 8/27/2017
 */
public class SeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    public static final int MAXIMUM_CAPACITY = 1 << 30;

    private List<AnyType>[] theLists;

    private int currentSize;

    public SeparateChainingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size){

        theLists = new LinkedList[nextPrime(size)];
        for(int i = 0; i < theLists.length; i++){
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    public void makeEmpty(){
        for(int i = 0; i < theLists.length; i++){
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private int nextPrime(int size) {
        int n = size - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public void insert(AnyType x){
        List<AnyType> whichList = theLists[hash(x)];
        if(!whichList.contains(x)){
            whichList.add(x);
            if(++ currentSize > theLists.length){
//                rehash();
            }
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public boolean contains(AnyType x){
        List<AnyType> whichList = theLists[hash(x)];
        return whichList.contains(x);
    }

    public void remove(AnyType x){
        List<AnyType> whichList = theLists[hash(x)];
        if(whichList.contains(x)){
            whichList.remove(x);
            currentSize--;
        }
    }


}
