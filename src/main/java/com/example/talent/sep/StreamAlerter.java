package com.example.talent.sep;


public class StreamAlerter {
//    private RingBuffer ring;
    private final Trie trie;

    public StreamAlerter(String[] strings) {
        trie = new Trie();
        for (String string : strings) {
            trie.addWordToTrie(string);
        }

    }

    public boolean query(char ch) {
        return trie.containWord(ch);
    }
}
