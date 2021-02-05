package com.ggx.question.leetcode.editor.cn;

//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 419 👎 0

public class ImplementTriePrefixTree{
    public static void main(String[] args) {

    }

 //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {

        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode trieNode = root;
            for(char c : word.toCharArray()){
                if(trieNode.treeNodes[c - 'a'] == null){
                    trieNode.treeNodes[c - 'a'] = new TrieNode();
                }
                trieNode = trieNode.treeNodes[c - 'a'];
            }
            trieNode.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode trieNode = root;
            for(char c : word.toCharArray()){
                if((trieNode = trieNode.treeNodes[c - 'a']) == null){
                    return false;
                }
            }
            return trieNode.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode trieNode = root;
            for(char c : prefix.toCharArray()){
                if((trieNode = trieNode.treeNodes[c - 'a']) == null){
                    return false;
                }
            }
            return true;
        }

        private class TrieNode{
            TrieNode[] treeNodes;
            boolean isEnd = false;

            TrieNode(){
                treeNodes = new TrieNode[26];
            }
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}