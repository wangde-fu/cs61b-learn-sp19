import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet AListFloor= new AListFloorSet();
        RedBlackFloorSet RedBlackFloor= new RedBlackFloorSet();
        for (int i = 0; i <100000; i++) {
            double random=StdRandom.uniform(-5000,5000);
            AListFloor.add(random);
            RedBlackFloor.add(random);
        }
        for (int i = 0; i <100000; i++) {
            double random=StdRandom.uniform(-5000,5000);
            double aAListFloor=AListFloor.floor(random);
            double aRedBlackFloor=RedBlackFloor.floor(random);
            assertEquals("expected is "+aAListFloor+"but actural is "+aRedBlackFloor,aAListFloor,aRedBlackFloor,0.000001);
        }
    }
}
