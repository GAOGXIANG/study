package com.ggx.datastructure.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ggx
 * @version 1.0
 * @date 2017/7/29 0029
 */
public class MyLinkedList<T> implements Iterable<T>{

    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList(){
        clear();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class Node<T>{
        public Node(T d, Node<T> p, Node<T> n){
            data = d;
            prev = p;
            next = n;
        }
        public T data;
        public Node<T> prev;
        public Node<T> next;
    }


    private void clear() {
        beginMarker = new Node<T> (null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(T x){
        add(size(), x);
        return true;
    }

    public void add(int idx, T x){
        addBefore(getNode(idx), x);
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<T>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private Node<T> getNode(int idx) {
        Node<T> p;
        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }

        if(idx < size()/2){
            p = beginMarker;
            for(int i = 0; i < idx; i++){
                p = p.next;
            }
        }else{
            p = endMarker;
            for(int i = size(); i > idx; i--){
                p = p.prev;
            }
        }
        return p;
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(int idx, T newVal){
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private T remove(Node<T> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        theSize++;
        modCount++;
        return node.data;
    }

    private class LinkedListIterator implements Iterator<T>{

        Node<T> current = beginMarker.next;
        int expectedModCount = modCount;
        boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }

    }
}
