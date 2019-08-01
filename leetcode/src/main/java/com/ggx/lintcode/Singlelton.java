package com.ggx.lintcode;

import java.util.Stack;

/**
 * @author ggx
 * @version 1.0
 * @date 2017/7/29
 */
public class Singlelton {

}

class Solution {

    private volatile static Solution single = null;

    /**
     * @return: The same instance of this class every time
     */
    //单例-懒汉模式
    public static Solution getInstance() {
        if (single == null) {
            single = new Solution();
        }
        return single;
    }

    public static synchronized Solution syncGetInstance(){
        if(single == null) single = new Solution();
        return single;
    }

    public static Solution getSolution(){
        if(single == null){
            synchronized(Solution.class){
                if(single == null){
                    single = new Solution();
                }
            }
        }
        return single;
    }

    public static ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        ListNode result = null;
        Stack<Integer> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        Stack<Integer> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!stack1.empty() || !stack2.empty() || carry != 0) {
            int sum = carry;
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            ListNode prev = new ListNode(sum%10);
            carry = sum/10;
            if(result == null){
                result = prev;
            }else{
                prev.next = result;
                result = prev;
            }
        }
        return result;
    }

    public static void main(String[] args){
        ListNode l1 = generateRoot(new Integer[]{2,8,2,8,2,9,6,4,5,2,5,2});
        ListNode l2 = generateRoot(new Integer[]{5,8,9,5,6,5,1,8,7,5,5});
        addLists2(l1, l2);
    }

    public static ListNode generateRoot(Integer[] array){
        ListNode result = null;
        ListNode temp = null;
        for(int i = 0; i<array.length; i++){
            ListNode next = new ListNode(array[i]);
            if(i == 0){
                result = next;
                temp = next;
            }else{
                temp.next = next;
                temp = next;
            }
        }
        return result;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

