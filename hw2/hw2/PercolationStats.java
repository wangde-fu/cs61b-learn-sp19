package hw2;

// import edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] fraction;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0||T<=0) {
            throw new IllegalArgumentException("N and T should bigger than 0!");
        }
        this.T=T;
        fraction=new double[T];

        for (int i=0;i<T ;i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row,col);
            }
            fraction[i] = (double) p.numberOfOpenSites() / (N*N);
        }
    }
    // sample mean of percolation threshold
    public double mean(){return StdStats.mean(fraction);}
    // sample standard deviation 偏差 of percolation threshold
    public double stddev(){return StdStats.stddev(fraction);}
    // low endpoint of 95% confidence interval
    public double confidenceLow(){return mean()-(1.96*stddev()/Math.sqrt(T));}
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){return mean()+(1.96*stddev()/Math.sqrt(T));}
}
