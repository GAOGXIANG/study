package com.ggx.question.leetcode.editor.cn;

//设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针
///引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。 
//
// 在链表类中实现这些功能： 
//
// 
// get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。 
// addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。 
// addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。 
// addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加
//到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。 
// deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。 
// 
//
// 
//
// 示例： 
//
// MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//linkedList.get(1);            //返回3
// 
//
// 
//
// 提示： 
//
// 
// 所有val值都在 [1, 1000] 之内。 
// 操作次数将在 [1, 1000] 之内。 
// 请不要使用内置的 LinkedList 库。 
// 
// Related Topics 设计 链表 
// 👍 169 👎 0

public class DesignLinkedList{
    public static void main(String[] args) {

    }

 //leetcode submit region begin(Prohibit modification and deletion)
    class MyLinkedList {

        private Node head;
        private Node tail;
        private int size = 0;

        /** Initialize your data structure here. */
        public MyLinkedList() {

        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index >= size) return -1;
            Node result = getNode(index);
            return result.val;
        }

         private Node getNode(int index) {
             Node result;
             if(index > size/2){
                 result = tail;
                 for(int i = size - index - 1; i > 0; i--){
                     result = result.prev;
                 }
             }else{
                 result = head;
                 for(int i = 0; i < index; i++){
                     result = result.next;
                 }
             }
             return result;
         }

         /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node node = new Node(val);
            if(size == 0){
                head = node;
                tail = node;
            }else{
                Node temp = head;
                head = node;
                node.next = temp;
                temp.prev = node;
            }
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node node = new Node(val);
            if(size == 0){
                head = node;
                tail = node;
            }else{
                Node temp = tail;
                tail = node;
                node.prev = temp;
                temp.next = node;
            }
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index > size) return;
            if(index == 0) {
                addAtHead(val);
            }else if(index == size){
                addAtTail(val);
            }else{
                Node node = getNode(index);
                Node newNode = new Node(val);
                node.prev.next = newNode;
                newNode.prev = node.prev;
                newNode.next = node;
                node.prev = newNode;
                size++;
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index >= size || index < 0) return;
            if(index == 0 && size == 1){
                head = null;
                tail = null;
            }else{
                Node node = getNode(index);
                Node prev = node.prev;
                Node next = node.next;
                if(prev != null && next != null){
                    //删的中间节点
                    prev.next = next;
                    next.prev = prev;
                }else if(prev == null){
                    //删的头节点
                    head = next;
                    next.prev = null;
                }else if(next == null){
                    //删的尾节点
                    prev.next = null;
                    tail = prev;
                }
            }
            size--;
        }

        private class Node{
            int val;
            Node next;
            Node prev;

            Node(){}
            Node(int val){
                this.val = val;
            }
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)

}