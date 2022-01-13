import java.util.Set;
//import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.HashSet;
import java.util.iterator;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int initialSize = 16;
    private static final double loadFactor = 0.75;

    private int size;
    private int threshold;
    private ListInBuckets<K, V>[] buckets;

    // 可以使用标准库中的 SequentialSearchST ，即一个无序链表
    // 这里自己实现 ListInBuckets
    private class ListInBuckets<K,V>{
        private K key;
        private V value;
        private ListInBuckets<K,V> next;
        // private int hashCode;

        // 初始化 ListInBuckets
        public ListInBuckets(K key,V value,ListInBuckets next){
            // 不储存hashCode
            this.key = key;
            this.value = value;
            this.next = next;
        }
        /*
        // 将hashCode储存到每个LinkedList中
        public ListInBuckets(K Key,V Value,ListInBuckets next,int hashCode){
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hashCode;
        }*/

        // 访问器方法
        // public int getHashCode() { return hashCode; }
        // public void setHashCode(int hashCode) { this.hashCode = hashCode; }
        public K getKey() { return key; }
        // public void setKey(K key) { this.key = key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
        public ListInBuckets<K, V> getNext() { return next; }
        public void setNext(ListInBuckets<K, V> next) { this.next = next; }
    }

    public MyHashMap(){
        buckets = new ListInBuckets[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }
    public MyHashMap(int the_initialSize){
        buckets = new ListInBuckets[the_initialSize];
        threshold = (int) (the_initialSize * loadFactor);
        size = 0;
    }
    public MyHashMap(int the_initialSize, double the_loadFactor){
        buckets = new ListInBuckets[the_initialSize];
        threshold = (int) (the_initialSize * the_loadFactor);
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        int the_hash=hash(key,buckets.length);

        ListInBuckets<K,V> the_list=buckets[the_hash];

        while(the_list!=null){
            if (the_list.getKey().equals(key)) {
                return the_list.getValue();
            }
            the_list=the_list.getNext();
        }
        return null;
    }

    /** Rewrite the hashCode for this class. */
    private int hash(K key, int length) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        // Cited from https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
        // 要求length为2的整数次幂
        // 它返回0-(length-1)之间的数字，故亦可作为数组索引
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (length-1);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){ return size;}

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value){}

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){}

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){}

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){}
}
