package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    // Index for the next dequeue or peek.
    private int first;
    // Index for the next enqueue.
    private int last;
    // Variable for the fillCount.
    private int fillCount;
    // Array for storing the buffer data.
    private T[] rb;

    public ArrayRingBuffer(int capacity) {
        first=0;
        last=0;
        fillCount=0;
        rb=(T[]) new Object[capacity];
    }

    private int nextId(int index){
        // return the index of the next element in the front or end.
        /*if (index==(rb.capacity()-1)) {
            return 0;
        }
        return (index+1);*/
        return (index+1==this.capacity()? 0:(last+1));
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (x==null) {
            throw new IllegalArgumentException("null is not allowed");
        }
        rb[last]=x;
        fillCount++;
        last=nextId(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T theX=rb[first];
        fillCount--;
        first=nextId(first);
        return theX;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }
    @Override
    public int fillCount(){
        return fillCount;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
