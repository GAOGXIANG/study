package com.ggx.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序树的实现
 */
public class TrieTree {

    private TrieTreeNode root;

    public TrieTree(){
        root = new TrieTreeNode();
    }

    public void insert(String word){
        char[] chars = word.toCharArray();
        TrieTreeNode node = root;
        for(char c : chars){
            if(!node.containsKey(c)){
                TrieTreeNode treeNode = new TrieTreeNode();
                treeNode.val = c - '0';
                node.nodes[treeNode.val] = treeNode;
                node.childCount++;
            }
            node = node.get(c);
        }
        node.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(word, true);
    }

    private boolean search(String word, boolean isWord){
        boolean find = true;
        TrieTreeNode node = root;
        for(int i = 0; i < word.length(); i++){
            if(node.containsKey(word.charAt(i))){
                node = node.get(word.charAt(i));
            }else{
                find = false;
                break;
            }
        }
        if(find && isWord){
            return node.end;
        }
        return find;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return search(prefix, false);
    }

    /**
     * 递归遍历
     */
    private void recursiveSearch(TrieTreeNode node, String path, List<String> result) {
        if(node.childCount == 0 && path.length() > 0){
            result.add(path);
            return;
        }
        for(int i = 0; i < node.nodes.length; i++){
            if(node.nodes[i] != null){
                recursiveSearch(node.nodes[i], path + node.nodes[i].val, result);
            }
        }
    }

    public List<String> getLeafPath(){
        List<String> result = new ArrayList<>();
        recursiveSearch(root, "", result);
        return result;
    }


    class TrieTreeNode{
        TrieTreeNode[] nodes;
        int val;
        int childCount = 0;
        boolean end = false;

        public TrieTreeNode(){
            nodes = new TrieTreeNode[10];
        }

        public boolean containsKey(char c){
            return nodes[c - '0'] != null;
        }

        public TrieTreeNode get(char c){
            return nodes[c - '0'];
        }
    }

//    public static void main(String[] args) {
//        String[] strings = new String[]{"01","011","0111","02","0211", "0212", "02111"};
//        TrieTree trieTree = new TrieTree();
//        trieTree.add(strings);
//        List<String> result = trieTree.getLeafPath();
//        for(String  str : result){
//            System.out.println(str);
//        }
//    }
}
