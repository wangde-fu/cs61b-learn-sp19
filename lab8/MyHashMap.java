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
    private class ListInBuckets<K, V> {
        private K key;
        private V value;
        private ListInBuckets<K, V> next;
        // private int hashCode;

        // 初始化 ListInBuckets
        public ListInBuckets(K key, V value, ListInBuckets next){
            // 不储存hashCode
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /*
         * //将hashCode储存到每个LinkedList中
         * public ListInBuckets(K Key,V Value,ListInBuckets next,int hashCode){
         *  this.key = key;
         *  this.value = value;
         *  this.next = next;
         *  this.hashCode = hashCode;
         * }
         */

        // 访问器方法
        // public int getHashCode() { return hashCode; }
        // public void setHashCode(int hashCode) { this.hashCode = hashCode; }
        public K getKey(){ return key; }
        // public void setKey(K key) { this.key = key; }
        public V getValue(){ return value; }
        public void setValue(V value){ this.value = value; }
        public ListInBuckets<K, V> getNext(){ return next; }
        public void setNext(ListInBuckets<K, V> next){ this.next = next; }
    }

    // 初始化MyHashMap
    public MyHashMap(){
        buckets = new ListInBuckets[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }

    public MyHashMap(int theInitialSize){
        buckets = new ListInBuckets[theInitialSize];
        threshold = (int) (theInitialSize * loadFactor);
        size = 0;
    }

    public MyHashMap(int theInitialSize, double theLoadFactor){
        buckets = new ListInBuckets[theInitialSize];
        threshold = (int) (theInitialSize * theLoadFactor);
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
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
        int theHash = hash(key, buckets.length);

        ListInBuckets<K, V> theList = buckets[theHash];

        while (theList != null) {
            if (theList.getKey().equals(key)) {
                return theList.getValue();
            }
            theList = theList.getNext();
        }
        return null;
    }

    /** Rewrite the hashCode for this class. */
    private int hash(K key, int length){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        // Cited from https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
        // 要求length为2的整数次幂
        // 它返回0-(length-1)之间的数字，故亦可作为数组索引
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (length - 1);
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
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            remove(key);
            return;
        }

        int theHash = hash(key, buckets.length);
        ListInBuckets<K, V> theList = buckets[theHash];

        while (theList != null) {
            if (theList.getKey().equals(key)) {
                // 更新value
                theList.setValue(value);
                return;
            }
            theList = theList.getNext();
        }
        put(theHash, key, value);
    }

    private void put(int theHash, K key, V value){
        // 把之前的LinkedList放在新节点的next可以简化写法
        ListInBuckets<K, V> theList = new ListInBuckets<>(key, value, buckets[theHash]);
        buckets[theHash] = theList;
        size++;
        if (size > threshold) {
            resize(buckets.length * 2);
        }
    }

    private void resize(int newLength){
        ListInBuckets<K, V>[] newBuckets = new ListInBuckets[newLength];
        for (int i = 0; i < buckets.length; i++) {
            ListInBuckets<K, V> theList = buckets[i];
            while (theList != null) {
                int newHashCode = hash(theList.getKey(), newLength);

                ListInBuckets<K, V> tempList = new ListInBuckets<>(theList.getKey(), theList.getValue(), newBuckets[newHashCode]);
                newBuckets[newHashCode] = tempList;

                theList = theList.getNext();
            }
        }
        buckets = newBuckets;
        threshold = (int) (newLength * loadFactor);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        Set<K> allKeys = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            ListInBuckets<K, V> theList = buckets[i];
            while (theList != null) {
                allKeys.add(theList.getKey());
                theList = theList.getNext();
            }
        }
        return allKeys;
    }

    @Override
    public Iterator<K> iterator(){
        return keySet().iterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        int theHash = hash(key, buckets.length);

        ListInBuckets<K, V> theList = buckets[theHash];
        while (theList != null) {
            if (theList.getKey().equals(key)) {
                // 删除节点
                remove(key, theList, theHash);
                size--;
                return theList.getValue();
            }
            theList = theList.getNext();
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        int theHash = hash(key, buckets.length);

        ListInBuckets<K, V> theList = buckets[theHash];
        while (theList != null) {
            if (theList.getKey().equals(key) && theList.getValue().equals(value)) {
                // 删除节点
                remove(key, theList, theHash);
                size--;
                return value;
            }
            theList = theList.getNext();
        }
        return null;
    }

    private void remove(K key, ListInBuckets theList, int theHash){
        // 链表第一个
        if (buckets[theHash].getKey().equals(key)) {
            buckets[theHash] = theList.getNext();
        }
        // 不是链表第一个
        LinkedList<K, V> tempList = buckets[theHash];
        while (!tempList.getNext().getKey().equals(key)) {
            tempList = tempList.getNext();
        }
        tempList.setNext(theList);
    }
}
