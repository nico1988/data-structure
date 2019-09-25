import java.util.TreeMap;

public class MapSum {

    private class Node{

        public int value; // value为0代表不是一个词  是不是一个词可以由value决定
        public TreeMap<Character, Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {

        root = new Node();
    }

    public void insert(String key, int val) {

        Node cur = root;
        for(int i = 0 ; i < key.length() ; i ++){
            char c = key.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.value = val; // 每个字母全部添加到trie中以后 添加完成以后需要添加value
    }

    public int sum(String prefix) {

        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node){
        // 为什么没有递归到底？ 因为下面已经包含了递归到底的情况了
        int res = node.value;
        for(char c: node.next.keySet()) // 这里包含了递归到底的情况
            res += sum(node.next.get(c)); // 当前node的value和node所有的子树的value值加入到res中 最后返回回去
        return res;
    }
}
