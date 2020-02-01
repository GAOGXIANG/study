package com.ggx.leetcode.hard.dynamic;

import java.util.Stack;

/**
 * 32. https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 最长有效括号
 */
public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        Stack<Integer> unmatchedPosition = new Stack<>();
        for(int i  = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                unmatchedPosition.push(i);
                dp[i] = 0;
            }else{
                if(!unmatchedPosition.isEmpty()){
                    int unmatched = unmatchedPosition.pop();
                    dp[i]=dp[i-1] + 2;
                    if(unmatched - 1 > 0){
                        dp[i] = dp[i] + dp[unmatched - 1];
                    }
                }else{
                    dp[i] = 0;
                }
            }
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "(()";
        longestValidParentheses(s);
    }

    /**=========================================单独用栈解决=========================================*/

    /**
     * 算法
     *
     * 与找到每个可能的子字符串后再判断它的有效性不同，我们可以用栈在遍历给定字符串的过程中去判断到目前为止扫描的子字符串的有效性，同时能的都最长有效字符串的长度。我们首先将 -1−1 放入栈顶。
     *
     * 对于遇到的每个 \text{‘(’}‘(’ ，我们将它的下标放入栈中。
     * 对于遇到的每个 \text{‘)’}‘)’ ，我们弹出栈顶的元素并将当前元素的下标与弹出元素下标作差，得出当前有效括号字符串的长度。通过这种方法，我们继续计算有效子字符串的长度，并最终返回最长有效子字符串的长度。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

//    public int longestValidParentheses(String s) {
//        int maxans = 0;
//        Stack<Integer> stack = new Stack<>();
//        stack.push(-1);
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(') {
//                stack.push(i);
//            } else {
//                stack.pop();
//                if (stack.empty()) {
//                    stack.push(i);
//                } else {
//                    maxans = Math.max(maxans, i - stack.peek());
//                }
//            }
//        }
//        return maxans;
//    }

}
