package com.ggx.question.leetcode.editor.cn;

//给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。 
//
// 示例 1: 
//
// 
//输入: [3, 2, 1]
//
//输出: 1
//
//解释: 第三大的数是 1.
// 
//
// 示例 2: 
//
// 
//输入: [1, 2]
//
//输出: 2
//
//解释: 第三大的数不存在, 所以返回最大的数 2 .
// 
//
// 示例 3: 
//
// 
//输入: [2, 2, 3, 1]
//
//输出: 1
//
//解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
//存在两个值为2的数，它们都排第二。
// 
// Related Topics 数组 
// 👍 169 👎 0

import java.util.LinkedList;
import java.util.ListIterator;

public class ThirdMaximumNumber{
    public static void main(String[] args) {
        Solution solution = new ThirdMaximumNumber().new Solution();
        
    }

 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int thirdMax(int[] nums) {
        long min = Long.MIN_VALUE, middle = Long.MIN_VALUE, max = Long.MIN_VALUE;
        for(int i : nums){
            if(i == min || i == middle || i == max){
                continue;
            }
            if(i > max){
                min = middle;
                middle = max;
                max = i;
            }else if(i > middle){
                min = middle;
                middle = i;
            }else if(i > min){
                min = i;
            }
        }
        return min == Long.MIN_VALUE ? new Long(max).intValue() : new Long(min).intValue();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}