public class ArrayDeque{
    private T[] items;
    private int nextFirst;//下次在头部增加时的索引
    private int nextLast;//下次在尾部增加时的索引
    private int size;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst=0;
        nextLast=1;
        size=0;
    }

    public int size() {return size;}

    public boolean isEmpty() {return size == 0;}
    public int plusOne(int index) {return (index+1)% items.length;}
    public int minusOne(int index) {return (index-1+items.length)% items.length;}
    public void resize(){
        if(size==items.length){
            // 扩大数组
            int newsize = items.length*2;
        }
        else if(size<=(items.length/3)&&items.length>8){
            // 缩小数组
            int newsize= items.length/2;
        }
        else{return;}

        @TODO
        items=(T[]) new Object[newsize];
        nextFirst=newsize-1;
        nextLast=1;
    }

    public void addFirst(T item){};

    public void addLast(T item){};
}
