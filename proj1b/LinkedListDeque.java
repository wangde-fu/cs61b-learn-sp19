public class LinkedListDeque<T> implements Deque<T> {
    private class LinkNode{
        private T item;
        private LinkNode prev;
        private LinkNode next;

        private LinkNode(T x,LinkNode p,LinkNode n){
            //this(x,p,n);
            item=x;
            prev=p;
            next=n;
        }
    }

    private LinkNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel= new LinkNode(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;
    };
    @Override
    public void addFirst(T item){
        size++;
        sentinel.next=new LinkNode(item,sentinel,sentinel.next);
        sentinel.next.next.prev=sentinel.next;
    }
    @Override
    public void addLast(T item){
        size++;
        sentinel.prev=new LinkNode(item,sentinel.prev,sentinel);
        sentinel.prev.prev.next=sentinel.prev;
    }

    @Override
    public int size() { return size;}
    @Override
    public void printDeque(){
        if (size==0) {return;}

        LinkNode tempNode=sentinel;
        for (int i = 1; i <size; i++){
            tempNode=tempNode.next;
            System.out.print(tempNode.item+" ");
        }
        System.out.println(sentinel.prev.item);
    }
    @Override
    public T removeFirst(){
        if (size==0) {return null;}
        size--;
        T theItem= sentinel.next.item;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        return theItem;
    }
    @Override
    public T removeLast(){
        if (size==0) {return null;}
        size--;
        T theItem= sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        return theItem;
    }
    @Override
    public T get(int index){
        if (index<1||index>size) {return null;}

        LinkNode tempNode= sentinel.next;
        for (int i=1; i<index; i++ ) {
            tempNode=tempNode.next;
        }
        return tempNode.item;
    }

    public T getRecursive(int index){
        return getRecursive(index,sentinel.next);
    }
    private T getRecursive(int index, LinkNode theLink){
        if (index==1) {return theLink.item;}
        return getRecursive(index-1,theLink.next);
    }
}
