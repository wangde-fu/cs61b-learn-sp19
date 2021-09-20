package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(9.3);
        arb.enqueue(4.2);
        assertEquals(2,arb.fillCount());
        //arb.enqueue(null);
        arb.enqueue(0);
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(-10);
        assertTrue(arb.isFull());
        assertEquals(9.3,arb.dequeue());
        assertEquals(4.2,arb.peek());
        assertEquals(4.2,arb.dequeue());
    }
}
