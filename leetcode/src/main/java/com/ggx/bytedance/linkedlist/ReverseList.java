package com.ggx.bytedance.linkedlist;

import com.ggx.leetcode.easy.linkedarray.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList {

    private ListNode reverseHead;
    public ListNode reverseList(ListNode head) {
        if(head == null) return head;
        reverse(head, head.next);
        return reverseHead;
    }

    private void reverse(ListNode node, ListNode next) {
        if(next == null){
            reverseHead = node;
            return;
        }
        reverse(next, next.next);
        next.next = node;
        node.next = null;
    }
}
