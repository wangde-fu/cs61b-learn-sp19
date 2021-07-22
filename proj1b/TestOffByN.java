import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(4);

    // Your tests go here.
    @Test
    public void testequalChars(){
        assertTrue(offByN.equalChars('a', 'e'));
        assertFalse(offByN.equalChars('a', 'a'));
        assertFalse(offByN.equalChars('a', 'f'));
        assertFalse(offByN.equalChars('a', 'z'));
        // assertTrue(offByN.equalChars('%', '&'));
    }
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
