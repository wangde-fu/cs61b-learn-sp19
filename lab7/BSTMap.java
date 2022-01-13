import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
// import static org.junit.Assert.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    //root中是储存值的
    private Node root;             // root of BST

    private class Node {
        private final K key;           // sorted by key
        private V val;         // associated data
        private int size;          // number of nodes in subtree
        private Node left, right;  // left and right subtrees

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.left=null;
            this.right=null;
        }
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size(){return size(root);}
    private int size(Node node){
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        //时刻不要忘记输入null的情况
        //我们也不允许输入value为空
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        //添加明显是一个迭代操作
        root = put(root, key, value);
    }
    private Node put(Node node,K key,V value){
        if (node==null) {
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp<0) {
            node.left = put(node.left,key,value);
        }else if (cmp>0) {
            node.right = put(node.right,key,value);
        }else {
            node.val=value;
        }
        //这里的size不需要迭代,所以直接调用size函数
        node.size=1+size(node.left)+size(node.right);
        return node;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        //迭代明显需要一个辅助函数
        return get(root,key);
    }
    private V get(Node node,K key){
        if (key==null) {
            throw new IllegalArgumentException();
        }
        if (node==null) {
            //null if this map contains no mapping for the key.
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp<0) {
            return get(node.left,key);
        } else if (cmp>0) {
            return get(node.right,key);
        } else {
            return node.val;
        }
    }

    public void printInOrder(){
        System.out.println("There are "+size()+" item(s) in this BSTMap.");
        if (root==null) {
            System.out.println("The BSTMap is empty!");
            return;
        }
        for (int i = 0; i < size(); i += 1) {
            System.out.println("Key:"+smallestNode(i).key + "     Value:" + smallestNode(i).val);
        }
        System.out.println("All the item(s) has been printed.");
        return;
    }
    /* printInOrder() helper.
     * Return the Node with (i + 1)st smallest key.
     */
    private Node smallestNode(int i){
        if (i<0||i>=size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return helpSmallestNode(i,root);
    }
    private Node helpSmallestNode(int i,Node node){
        if (node.left==null) {
            //TODO
            if (i==0) {
                return node;
            }else{
                return helpSmallestNode(i-1,node.right);
            }
        } else{
            Node leftNode=node.left;
            if (size(leftNode)<i) {
                //node.right不会为空
                return helpSmallestNode(i-size(leftNode)-1,node.right);
            }else if (size(leftNode)>i) {
                return helpSmallestNode(i,leftNode);
            }else{
                return node;
            }
        }
    }

    @Override
    /* Returns a Set view of the keys contained in this map. */
    //可以按照大小顺序输入Set
    /*public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        for (int i = 0; i < size(); i += 1) {
            BSTSet.add(smallestKey(i).key);
        }
        return BSTSet;
    }*/
    //按照Pre-order Traversal，即先访问父节点，再依次访问左右节点
    public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        preOrder(root,BSTSet);
        return BSTSet;
    }
    private void preOrder(Node node,Set<K> BSTSet) {
        if (node == null) { return; }
        // print(node.key)
        BSTSet.add(node.key);
        preOrder(node.left,BSTSet);
        preOrder(node.right,BSTSet);
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator(root);
    }
    public Iterator<K> iterator(Node node) {
        return new MyIterator(node);
    }
    private class MyIterator implements Iterator{
        private Stack<K> stack = new Stack<>();
        public MyIterator(Node node){
            for(int i=0;i<size(node);i++){
                preOrder(node,stack);
            }
        }
        private void preOrder(Node node,Stack<K> stack) {
            if (node == null) { return; }
            stack.push(node.key);
            preOrder(node.left,stack);
            preOrder(node.right,stack);
        }

        @Override
        public boolean hasNext(){ return !stack.isEmpty(); }
        @Override
        public K next(){ return stack.pop(); }
        /*@Override
        public void remove(){
            K key=stack.pop();
            V val=BSTMap.remove(key);
        }*/


        //一种很有思路的方法
        /*public BSTIterator(Node src) {
            while (src != null) {
                // Push root node and all left nodes to the stack.
                stack.push(src);
                src = src.left;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public K next() {
            Node curr = stack.pop();

            if (curr.right != null) {
                Node temp = curr.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }*/
    }

    @Override
    public V remove(K key, V value) {
        V theVal=get(key);
        if (theVal==null) {
            return null;
        } else if (!get(key).equals(value)) {
            return null;
        }else {
            return remove(key);
        }
    }
    @Override
    public V remove(K key) {
        V theVal = get(key);
        root = remove(root, key);
        return theVal;
    }
    private Node remove(Node node,K key){
        int cmp = key.compareTo(node.key);
        if (cmp<0) {
            node.size--;
            node.left = remove(node.left,key);
        } else if (cmp>0) {
            node.size--;
            node.right = remove(node.right,key);
        } else {
            if (size(node)==1) {
                return null;
            }
            return reBuild(node);
        }
        return node;
    }
    private Node reBuild(Node node){
        //右节点取代原节点，将左子树挂到右子树最小值的左面
        Node theNode = helpSmallestNode(0,node.right);
        theNode.left=node.left;
        return node.right;
    }

    public static void main(String[] args) {
        /*BSTMap theMap=new BSTMap();
        assertEquals(0, theMap.size());
        theMap.put(1,"first");
        assertEquals(1, theMap.size());
        assertTrue(theMap.containsKey(1));
        theMap.clear();
        for (int i=0;i<20;i++) {
            theMap.put(i,"dog"+i);
        }
        assertEquals(20, theMap.size());
        theMap.printInOrder();*/
    }
//TODO:尝试使用红黑树重写此二叉树

}
