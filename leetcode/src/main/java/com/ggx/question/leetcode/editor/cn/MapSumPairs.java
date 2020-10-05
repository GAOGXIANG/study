package com.ggx.question.leetcode.editor.cn;

//实现一个 MapSum 类里的两个方法，insert 和 sum。 
//
// 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。 
//
// 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。 
//
// 示例 1: 
//
// 输入: insert("apple", 3), 输出: Null
//输入: sum("ap"), 输出: 3
//输入: insert("app", 2), 输出: Null
//输入: sum("ap"), 输出: 5
// 
// Related Topics 字典树 
// 👍 57 👎 0

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MapSumPairs{
    public static void main(String[] args) {

    }

 //leetcode submit region begin(Prohibit modification and deletion)
    class MapSum {

        private TrieNode root;

        /** Initialize your data structure here. */
        public MapSum() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            TrieNode trieNode = root;
            for(char c : key.toCharArray()){
                if(!trieNode.trieNodeMap.containsKey(c)){
                    trieNode.trieNodeMap.put(c, new TrieNode());
                }
                trieNode = trieNode.trieNodeMap.get(c);
            }
            trieNode.val = val;
        }

        public int sum(String prefix) {
            int[] sum = new int[1];
            TrieNode trieNode = root;
            for(char c : prefix.toCharArray()){
                if((trieNode = trieNode.trieNodeMap.get(c)) == null){
                    break;
                }
            }
            if(trieNode != null){
                recursiveSum(trieNode, sum);
            }
            return sum[0];
        }

         private void recursiveSum(TrieNode trieNode, int[] sum) {
            sum[0] = sum[0] + trieNode.val;
            if(trieNode.trieNodeMap.size() == 0) return;
            for(TrieNode node : trieNode.trieNodeMap.values()){
                recursiveSum(node, sum);
            }
         }

         class TrieNode{
            Map<Character, TrieNode> trieNodeMap;
            int val;
            TrieNode(){
                trieNodeMap = new HashMap<>();
            }
         }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}