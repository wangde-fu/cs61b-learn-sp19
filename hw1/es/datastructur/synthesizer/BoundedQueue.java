package es.datastructur.synthesizer;

public interface BoundedQueue<T>{
    int capacity();   //return size of the buffer
    int fillCount(); //return number of elements currently in the buffer
    void enqueue(T value); //add value to the end
    T dequeue(); //remove and return value from the front
    T peek(); //return (but not delete) the value from the front

    default boolean isEmpty(){ //is the buffer empty?
        return fillCount() == 0;
    }
    default boolean isFull(){ //is the buffer full?
        return fillCount() ==capacity();
    }
}
