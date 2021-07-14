import static org.junit.Assert.*;
import org.junit.Test;
public class Fliktest{
    @Test
    public void test1(){
        int a=127;
        for (int b = 0; b < 200; b++) {
            boolean iftrue=Flik.isSameNumber(a,b);
            assertFalse("The numbers are equal with a="+a+" and b="+b,iftrue);
        }
    }
    @Test
    public void test2(){
        int a=128;int b=128;
        boolean iftrue=Flik.isSameNumber(a,b);
        assertTrue("The numbers are NOT equal with a="+a+" and b="+b,iftrue);
    }
}
