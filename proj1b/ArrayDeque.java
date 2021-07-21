public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int nextFirst;//下次在头部增加时的索引
    private int nextLast;//下次在尾部增加时的索引
    private int size;
    //没有设哨兵节点

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst=0;
        nextLast=1;
        size=0;
    }
    @Override
    public int size() {
        return size;
    }
    public int plusOne(int index) {
        return (index + 1) % items.length;
    }
    public int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    public void resize(){
        // 判断是否需要改变数组大小
        int newsize;
        if(size==items.length){
            // 扩大数组
            newsize = items.length*2;
        }
        else if(size<=(items.length/3)&&items.length>8){
            // 缩小数组
            newsize= items.length/2;
        }
        else{return;}

        int theFirst=plusOne(nextFirst);
        T[] newitems=(T[]) new Object[newsize];
        for (int i=0; i<size; i++) {
            newitems[i]=items[theFirst];
            theFirst=plusOne(theFirst);
        }
        items=newitems;
        nextFirst=newsize-1;
        nextLast=size;
    }
    @Override
    public T get(int index){
        // 获取第index的项目
        if (index>size) {return null;}

        int theFirst=plusOne(nextFirst);
        return items[(index+theFirst)%items.length];
    }
    @Override
    public void addFirst(T item){
        resize();
        items[nextFirst]=item;
        nextFirst=minusOne(nextFirst);
        size++;
    };
    @Override
    public void addLast(T item){
        resize();
        items[nextLast]=item;
        nextLast=plusOne(nextLast);
        size++;
    };
    @Override
    public void printDeque(){
        // 打印所有项目
        if (size==0) {
            System.out.println("");
            return;
        }

        int theFirst=plusOne(nextFirst);
        for (int i=1; i<size; i++) {
            System.out.print(items[theFirst]+" ");
            theFirst=plusOne(theFirst);
        }
        System.out.println(items[minusOne(nextLast)]);
    };
    @Override
    public T removeFirst(){
        resize();
        if(size == 0){return null;}
        nextFirst=plusOne(nextFirst);
        T theItem=items[nextFirst];
        items[nextFirst] = null;
        size--;
        return theItem;
    };
    @Override
    public T removeLast(){
        resize();
        if(size == 0){return null;}
        nextLast=minusOne(nextLast);
        T theItem=items[nextLast];
        items[nextLast] =null;
        size--;
        return theItem;
    };
}
