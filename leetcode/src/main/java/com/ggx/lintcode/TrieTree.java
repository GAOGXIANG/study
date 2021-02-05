package com.ggx.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 *一堆序号比如01，011，0111，02，021，0212，02111等，代表层级结构
 * 如何找出最底层的序号？比如上面的0111，0212和02111
 */
public class TrieTree {

    private TrieTreeNode root = new TrieTreeNode();

    public void add(String[] array){
        for(String str : array){
            char[] chars = str.toCharArray();
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
        }
    }

    public List<String> getLeafPath(){
        List<String> result = new ArrayList<>();
        recursiveSearch(root, "", result);
        return result;
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


    class TrieTreeNode{
        TrieTreeNode[] nodes;
        int val;
        int childCount = 0;

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

    public static void main(String[] args) {
        String[] strings = new String[]{"01","011","0111","02","0211", "0212", "02111"};
        TrieTree trieTree = new TrieTree();
        trieTree.add(strings);
        List<String> result = trieTree.getLeafPath();
        for(String  str : result){
            System.out.println(str);
        }
    }
}
