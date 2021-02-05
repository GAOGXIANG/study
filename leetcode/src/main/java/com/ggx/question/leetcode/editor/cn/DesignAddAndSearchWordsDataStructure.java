package com.ggx.question.leetcode.editor.cn;

//如果数据结构中有任何与word匹配的字符串，则bool search（word）返回true，否则返回false。 单词可能包含点“。” 点可以与任何字母匹
//配的地方。 
//
// 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。 
//
// 实现词典类 WordDictionary ： 
//
// 
// WordDictionary() 初始化词典对象 
// void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 
// bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '
//.' ，每个 . 都可以表示任何一个字母。 
// 
//
// 
//
// 示例： 
//
// 输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search","se
//arch"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 500 
// addWord 中的 word 由小写英文字母组成 
// search 中的 word 由 '.' 或小写英文字母组成 
// 最调用多 50000 次 addWord 和 search 
// 
// Related Topics 设计 字典树 回溯算法 
// 👍 165 👎 0

import java.util.HashMap;
import java.util.Map;

public class DesignAddAndSearchWordsDataStructure{
    public static void main(String[] args) {

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class WordDictionary {

        private TrieNode root;


        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()){
                if(!node.trieNodeMap.containsKey(c)){
                    node.trieNodeMap.put(c, new TrieNode());
                }
                node = node.trieNodeMap.get(c);
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return search(root, word.toCharArray(), 0);
        }

        private boolean search(TrieNode node, char[] chars, int i) {
            if(chars.length == i) return node.isEnd;
            boolean result = false;
            if(node.trieNodeMap.containsKey(chars[i])){
                result = search(node.trieNodeMap.get(chars[i]), chars, i+1);
            }else if(chars[i] == '.'){
                for(TrieNode trieNode : node.trieNodeMap.values()){
                    result = search(trieNode, chars, i+1);
                    if(result){
                        break;
                    }
                }
            }
            return result;
        }

        private class TrieNode {
            Map<Character, TrieNode> trieNodeMap;
            boolean isEnd = false;

            TrieNode() {
                trieNodeMap = new HashMap<>();
            }
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}