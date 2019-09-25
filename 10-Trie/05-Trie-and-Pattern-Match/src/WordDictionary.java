/// Leetcode 211. Add and Search Word - Data structure design
/// https://leetcode.com/problems/add-and-search-word-data-structure-design/description/

import java.util.TreeMap;

public class WordDictionary {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true; // 加完了最后一步设置isWord是true
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0); // 从0开始
    }

    private boolean match(Node node, String word, int index){

        if(index == word.length())
            return node.isWord; // 来到了一个节点表示一个单词

        char c = word.charAt(index);  // 当前的index  2种可能  一个. 二就是一个字母

        if(c != '.'){
            if(node.next.get(c) == null)
                return false; // 字符串匹配失败
            return match(node.next.get(c), word, index + 1); // 匹配成功了直接去下一个节点
        }
        else{
            for(char nextChar: node.next.keySet()) // 等于点 所有的字符都能匹配这个点 node的next的所有的映射
                if(match(node.next.get(nextChar), word, index + 1))
                    return true;
            return false;
        }
    }
}
