package com.ggx.leetcode.hard.linkedlist;

import com.ggx.leetcode.easy.linkedarray.ListNode;

import java.util.ArrayDeque;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *  
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *  
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class ReverseKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        ArrayDeque<ListNode> deque = new ArrayDeque<>(k);
        while(head != null){
            deque.add(head);
            head = head.next;
            if(deque.size() == k){
                while(!deque.isEmpty()){
                    prev.next = deque.pollLast();
                    prev = prev.next;
                    prev.next = null;
                }
            }
        }
        while(!deque.isEmpty()){
            prev.next = deque.pop();
            prev = prev.next;
            prev.next = null;
        }
        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
//        ListNode node2 = new ListNode(3);
//        node1.next = node2;
//        ListNode node3 = new ListNode(4);
//        node2.next = node3;
//        ListNode node4 = new ListNode(5);
//        node3.next = node4;
        reverseKGroup(head, 2);
    }

    //leetcode精选答案，速度更快
//    public ListNode reverseKGroup(ListNode head, int k) {
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//
//        ListNode pre = dummy;
//        ListNode end = dummy;
//
//        while (end.next != null) {
//            for (int i = 0; i < k && end != null; i++) end = end.next;
//            if (end == null) break;
//            ListNode start = pre.next;
//            ListNode next = end.next;
//            end.next = null;
//            pre.next = reverse(start);
//            start.next = next;
//            pre = start;
//
//            end = pre;
//        }
//        return dummy.next;
//    }
//
//    private ListNode reverse(ListNode head) {
//        ListNode pre = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode next = curr.next;
//            curr.next = pre;
//            pre = curr;
//            curr = next;
//        }
//        return pre;
//    }

}
