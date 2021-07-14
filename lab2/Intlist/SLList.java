public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        /* Recursive version */
        addLast(first, x);
    }

    private void addLast(IntNode node, int x) {
        if(node.next == null){
            node.next = new IntNode(x, null);
        }
        else{
            addLast(node.next, x);
        }
    }

    /** Returns the number of items in the list using recursion. */
    public int size() {
        /* Your Code Here! */
        /* Recursive version */
        return size(first);
    }

    private static int size(IntNode node) {
        if(node == null){
            return 0;
        }
        return 1 + size(node.next);
    }

    public static void main(String[] args) {
        SLList L=new SLList(15);
        L.addFirst(10);
        L.addLast(5);
    }
}
