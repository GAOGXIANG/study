package com.ggx.leetcode.hard.divide;

import com.ggx.leetcode.easy.linkedarray.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * <p>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }


    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;
        int mid = l + ((r - l) >> 1);
        return mergeTwoList(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    private ListNode mergeTwoList(ListNode lNode, ListNode rNode) {

        if (lNode == null) {
            return rNode;
        }
        if (rNode == null) {
            return lNode;
        }
        ListNode mergeListNode = null;
        ListNode current = null;
        while (lNode != null && rNode != null) {
            ListNode temp;
            if (lNode.val > rNode.val) {
                temp = new ListNode(rNode.val);
                rNode = rNode.next;
            } else {
                temp = new ListNode(lNode.val);
                lNode = lNode.next;
            }
            if (current == null) {
                current = temp;
                mergeListNode = current;
            } else {
                current.next = temp;
                current = temp;
            }

        }
        if (lNode != null) current.next = lNode;
        if (rNode != null) current.next = rNode;
        return mergeListNode;
    }

}
