package com.ggx.leetcode.medium.linkedlist;

import com.ggx.leetcode.easy.linkedarray.ListNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *  
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> l1Stack = new Stack<>();
        Stack<ListNode> l2Stack = new Stack<>();
        ListNode l1Node = l1; ListNode l2Node = l2;
        while(l1Node != null || l2Node != null){
            if(l1Node != null){
                l1Stack.push(l1Node);
                l1Node = l1Node.next;
            }
            if(l2Node != null){
                l2Stack.push(l2Node);
                l2Node = l2Node.next;
            }
        }
        ListNode node = null;
        int carry = 0, sum = 0;
        while(!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry == 1){
            sum+=carry;
            if(!l1Stack.isEmpty()){
                sum = sum + l1Stack.pop().val;
            }
            if(!l2Stack.isEmpty()){
                sum += l2Stack.pop().val;
            }
            ListNode temp = new ListNode(sum % 10);
            if (node != null) {
                temp.next = node;
            }
            node = temp;
            carry = sum / 10;
            sum = 0;
        }
        return node;
    }
}
