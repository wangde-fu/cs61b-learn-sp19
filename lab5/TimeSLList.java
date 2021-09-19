import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    public static int ops=10000;
    public static int N[]= {128000,64000,32000,16000,8000,4000,2000,1000};
    public static int lengthOfN=N.length;

    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        List<Integer> aNs = new ArrayList<>(lengthOfN);
        List<Double> atimes = new ArrayList<>(lengthOfN);
        List<Integer> aopCounts = new ArrayList<>(lengthOfN);

        for(int i= 0;i<N.length;i++){
            SLList<Integer> testSLList=new SLList<>();

            System.out.println("Looping with N="+N[lengthOfN-1]);
            for (int j = 0; j <N[lengthOfN-1];j++){
                testSLList.addLast(1);
            }
            lengthOfN--;
            Stopwatch sw = new Stopwatch();
            for (int k=0;k<ops;k++) {
                testSLList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            aNs.add(N[lengthOfN]);
            atimes.add(timeInSeconds);
            aopCounts.add(ops);
        }
        printTimingTable(aNs,atimes,aopCounts);

    }

}
