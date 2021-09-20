package es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 0.996; // energy decay factor
    public int capacity;

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // Create a buffer with capacity = SR / frequency. You'll need to
        // cast the result of this division operation into an int. For
        // better accuracy, use the Math.round() function before casting.
        // Your buffer should be initially filled with zeros.
        if (frequency <=0) {
            throw new IllegalArgumentException("frequency must be positive");
        }
        capacity = (int)Math.round(SR / frequency);
        buffer=new ArrayRingBuffer<Double>(capacity);
        for (int i = 0;i<capacity;i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in buffer, and replace with random numbers
        // between -0.5 and 0.5. You can get such a number by using:
        // double r = Math.random() - 0.5;
        //
        // Make sure that your random numbers are different from each
        // other.
        for (int i = 0;i<capacity;i++) {
            buffer.dequeue();
            buffer.enqueue(randomDouble());
        }

    }
    private double randomDouble() {
        return ((double)Math.random()-0.5);
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        // the average of the two multiplied by the DECAY factor.
        // Do not call StdAudio.play().
        double frontSample=buffer.dequeue();
        buffer.enqueue(0.5*DECAY*(frontSample+sample()));
    }
    public void tic(boolean damper) {
        double frontSample=buffer.dequeue();
        // System.out.println(damper);
        double aDECAY=damper?0.95:DECAY;
        buffer.enqueue(0.5*aDECAY*(frontSample+sample()));
        // buffer.enqueue(0.0);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // Return the correct thing.
        return buffer.peek();
    }
}
