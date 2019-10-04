import java.util.TreeMap;

public class HashTable<K, V> {

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M; // 选择一个合适的素数

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for(int i = 0 ; i < M ; i ++)
            hashtable[i] = new TreeMap<>();
    }

    public HashTable(){
        this(97);
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M; // 最高位是符号位  最高位是1的话是负数 相当于把符号位去掉
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        // 复用treemap的方法 所以简单
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key))
            map.put(key, value);
        else{
            map.put(key, value);
            size ++;
        }
    }

    public V remove(K key){
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");

        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }
}
