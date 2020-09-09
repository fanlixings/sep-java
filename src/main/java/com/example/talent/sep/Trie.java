package com.example.talent.sep;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @version v1.0
 * @ProjectName: sep-java
 * @ClassName: Trie
 * @Description: TODO
 * @Author: fanlx
 * @Date: 2020-09-08 10:41
 */
public class Trie {

    private static class Node {

        //如果这个节点没有子节点,就为true,如果有就位false
        public boolean isEnd;

        //存放当前节点的值和它的子节点
        public HashMap<Character, Node> children;

        public Node(boolean isEnd) {
            this.isEnd = isEnd;
            this.children = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    //这个Trie树的根节点
    private final Node root;

    public Trie() {
        this.root = new Node();
    }

    /**
     * 把所有带敏感词的字符串加入trie树中
     *
     * @param word
     */
    public void addWordToTrie(String word) {

        Node currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!currentNode.children.containsKey(c)) {
                currentNode.children.put(c, new Node());
            }
            currentNode = currentNode.children.get(c);
        }
        if (!currentNode.isEnd) {
            currentNode.isEnd = true;
        }
    }


    /**
     * 查找敏感词
     *
     * @param c
     * @return
     */

    private int count =1;
    public boolean containWord(char c) {
        HashMap<Character, Node> children = root.children;

        if(Character.isUpperCase(c)){
            if (children.containsKey(c)) {
                count++;
                return count >= 4;
            }
        }

        for (Map.Entry<Character, Node> characterNodeEntry : children.entrySet()) {
            Node value = characterNodeEntry.getValue();
            Character key = characterNodeEntry.getKey();

            if (key == c && value.isEnd) {
                return true;
            } else {
                children = value.children;

                for (Map.Entry<Character, Node> nodeEntry : children.entrySet()) {
                    if (nodeEntry.getKey() == c && nodeEntry.getValue().isEnd) {
                        return true;
                    } else {
                        children = nodeEntry.getValue().children;
                        for (Map.Entry<Character, Node> entry : children.entrySet()) {
                            if (entry.getKey() == c && entry.getValue().isEnd) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}


/**
 * 查找敏感词
 *
 * @param c
 * @return
 *//*
    public boolean containWord(char c) {

        String str = "博戏丸X";

        for (char c1 : str.toCharArray()) {
            if (c == c1) {
                Node currentNode = root;

                if (currentNode.children.containsKey(c)) {
                    return true;
                } else {
                    HashMap<Character, Node> children = currentNode.children;

                    for (Map.Entry<Character, Node> characterNodeEntry : children.entrySet()) {
                        Character key = characterNodeEntry.getKey();
                        currentNode = characterNodeEntry.getValue();

                        while (!(currentNode.children.size() == 0)) {
                            children = currentNode.children;

                            if (children.containsKey(c)) {
                                return true;
                            } else {
                                for (Map.Entry<Character, Node> nodeEntry : children.entrySet()) {
                                    Character key1 = nodeEntry.getKey();
                                    currentNode = nodeEntry.getValue();
                                    if (currentNode.children.containsKey(c)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }else {
                return false;
            }
        }
       return false;
    }*/

