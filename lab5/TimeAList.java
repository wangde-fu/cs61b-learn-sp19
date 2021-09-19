import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    public static int N[]= {128000,64000,32000,16000,8000,4000,2000,1000};
    public static int lengthOfN = N.length;

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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        List<Integer> aNs = new ArrayList<>(lengthOfN);
        List<Double> atimes = new ArrayList<>(lengthOfN);
        // List<Integer> opCounts = new ArrayList<>(doubleN);

        /*AList<Integer> testAList=new AList<>();

        double programStart = System.currentTimeMillis();
        for(int i= 0;i<N[0];i++){
            testAList.addLast(1);
            if(i==N[lengthOfN-1]){
                lengthOfN--;
                double programNow= System.currentTimeMillis();
                aNs.add(N[lengthOfN]);
                atimes.add(0.001*(programNow-programStart));
            }
        }*/
        // I forgot to use a stopwatch, and I used currentTimeMillis instead above.

        for(int i= 0;i<N.length;i++){
            AList<Integer> testAList=new AList<>();

            // System.out.println("Looping with N="+N[lengthOfN-1]);
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j <N[lengthOfN-1];j++){
                testAList.addLast(1);
            }
            lengthOfN--;
            double timeInSeconds = sw.elapsedTime();
            aNs.add(N[lengthOfN]);
            atimes.add(timeInSeconds);
        }

        printTimingTable(aNs,atimes,aNs);
    }
}
