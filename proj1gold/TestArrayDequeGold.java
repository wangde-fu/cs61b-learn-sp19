import static org.junit.Assert.*;
import org.junit.Test;
//我承认这个项目完全没看懂题目，是抄的GitHub上现成的作业
//果然是自己自学能力太差。但是题目里这么短的提示，根本不可能看出来怎么做吧(#`O′)
//目前理解为调用每个方法，随机输入，比较输出
//下面这两个import，去搜索引擎查了才知道。猜测GitHub上的作业引用它俩的原因是需要在出现故障时显示故障的情况。
import java.util.ArrayList;
import java.util.List;
//这里的测试，按照说明，应当一个一个测试，全部写好后再测试根本什么也不能测出来

public class TestArrayDequeGold{
    @Test
    public void testArrayDeque(){
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        //addFirst
        for (int i = 0; i<12; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
            assertEquals("Oh No!\nThis is bad in addFirst() or size():\n   size " + sad.size()
                            + " not equal to " + ads.size() + "!",
                    ads.size(),sad.size());
        }
        for (int i=0; i<12; i++) {
            int actual = sad.get(i);
            int expected = ads.get(i);
            assertEquals("Oh No!\nThis is bad in addFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected,actual);
        }
        //addLast
        for (int i = 0; i<12; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }
        for (int i=0; i<12; i++) {
            Integer actual = sad.get(i+12);
            Integer expected = ads.get(i+12);
            assertEquals("Oh No!\nThis is bad in addLast():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected,actual);
        }
        //removeFirst()
        for (int i =0; i<12; i++) {
            //拿出来的First正确
            int actual = sad.removeFirst();
            int expected = ads.removeFirst();
            assertEquals("Oh No!\nThis is bad in removeFirst():\n   Random number " + actual
            + " not equal to " + expected + "!",
            expected,actual);
            assertEquals("Oh No!\nThis is bad in size of removeFirst():\n   size " + sad.size()
                            + " not equal to " + ads.size() + "!",
                    ads.size(),sad.size());
        }
        for(int i =0; i<12; i++){
            //剩下来的序列正确
            Integer actual = sad.get(i);
            Integer expected = ads.get(i);
            assertEquals("Oh No!\nThis is bad in removeFirst():\n   Random number " + actual
            + " not equal to " + expected + "!",
            expected,actual);
        }
        //removeLast()
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i =0; i<12; i++) {
            //为了构建出错后可以查看错误的序列
            actualList.add(sad.removeLast());
            expectedList.add(ads.removeLast());
            assertEquals("Oh No!\nThis is bad in size of removeLast():\n   size " + sad.size()
            + " not equal to " + ads.size() + "!",
            ads.size(),sad.size());
        }
        for(int i =0; i<12; i++){
            assertEquals("Oh No!\nThis is bad in removeLast():\n   Random number " + actualList.get(i)
            + " not equal to " + expectedList.get(i) + "!",
            expectedList.get(i),actualList.get(i));
        }
    }
}
