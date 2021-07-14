/** Array based list.
 *  @author Josh Hug
 */

public class AList<Glorp> {
    private Glorp[] items;
    private int size;
    /** Creates an empty list. */
    public AList(int length) {
        items = (Glorp []) new Object[length];
        size=0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Glorp x) {
        if(size== items.length){
            //AList theList1=new AList(size*2);
            Glorp[] a=(Glorp[]) new Object[size*2];
            System.arraycopy(items,0,a,0,size);
            items=a;
        }
        items[size]=x;
        size++;
        //System.out.println(items.length);
    }
    /** Returns the item from the back of the list. */
    public Glorp getLast() {
        return items[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Glorp get(int i) {
        if(i>size) {
            System.out.println("超出索引范围，索引范围是"+size);
            return null;
        }
        return items[i-1];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Glorp removeLast() {
        size--;
        return items[size];
    }
    public static void main(String[] args){
        AList<Integer> theList=new AList<>(2);
        theList.addLast(2);
        theList.addLast(7);
        theList.addLast(9);
        System.out.println(theList.getLast());
        System.out.println(theList.get(2));
        System.out.println(theList.size());
        System.out.println(theList.removeLast());
    }
} 