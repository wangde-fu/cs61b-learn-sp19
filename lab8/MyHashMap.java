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
        private K Key;
        private V Value;
        private ListInBuckets<K,V> next;
        private int hashCode;

        // 初始化 ListInBuckets
        public ListInBuckets(K Key,V Value,ListInBuckets next,int hashCode){
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hashCode;
        }

        // 访问器方法
        public int getHashCode() { return hashCode; }
        public void setHashCode(int hashCode) { this.hashCode = hashCode; }
        public K getKey() { return key; }
        // public void setKey(K key) { this.key = key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
        public BucketEntity<K, V> getNext() { return next; }
        public void setNext(BucketEntity<K, V> next) { this.next = next; }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(K key){}

    @Override
    public V get(K key){}

    @Override
    public int size(){}

    @Override
    public void put(K key, V value){}

    @Override
    public Set<K> keySet(){}

    @Override
    public V remove(K key){}

    @Override
    public V remove(K key, V value){}
}
