package com.ggx.bytedance.linkedlist;

import com.ggx.leetcode.easy.linkedarray.ListNode;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        int count = 1;
        while(fast.next != null){
            fast = fast.next;
            if(count++%2 == 0){
                slow = slow.next;
            }
        }
        fast = slow.next;
        slow.next = null;
        ListNode l = sortList(head);
        ListNode h = sortList(fast);
        ListNode prev = new ListNode(-1);
        ListNode tmp = prev;
        while(l != null && h != null){
            if(l.val < h.val){
                tmp.next = l;
                tmp = tmp.next;
                l = l.next;
            }else{
                tmp.next = h;
                tmp = tmp.next;
                h = h.next;
            }
        }
        if(l != null) tmp.next = l;
        if(h != null) tmp.next = h;
        return prev.next;
    }
}
