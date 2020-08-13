package com.ggx.bytedance.linkedlist;

import com.ggx.leetcode.easy.linkedarray.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 */
public class IntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode l1 = headA, l2 = headB;
        while(l1 != l2){
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }
}
