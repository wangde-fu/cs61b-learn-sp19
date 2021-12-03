package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// You can find it's description document here:
// https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html
// https://www.cnblogs.com/anne-vista/p/4841732.html

public class Percolation {
    private boolean[] isOpened;
    private boolean connectTop[];
    private boolean connectBottom[];
    private int N;
    private boolean isPercolation;
    private WeightedQuickUnionUF uf;
    // 为了防止backwash的问题，即只与底部相连的空间不应当有水，但是如果把底部和顶部加入并查集，就会导致这个问题。
    
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        this.N=N;
        connectTop=new boolean[N*N];
        connectBottom=new boolean[N*N];
        isOpened=new boolean[N*N];
        uf= new WeightedQuickUnionUF(N*N);

        if (N <= 0) {
            throw new IllegalArgumentException("N should bigger than 0!");
        }

        for (int i = 0; i < N*N; i++) {
             isOpened[i] = false;
             connectTop[i] = false;
             connectBottom[i] = false;
         }
         percolateFlag = false;
    }

    // transform (row, col) to an array
    private int xy2array(int row,int col){
        validateRowCol(row,col);
        //top对应数组的0,bottom对应N*N+1
        return (row-1)*N+col-1;
    }
    //
    private void validateRowCol(int row,int col){
        throw new IndexOutOfBoundsException("Index is not betwwen 1 and N");
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row,col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row,col);
        return isOpened[xy2array(row,col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row,col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int numberOfOpen=0;
        for (i : isOpened) {
            numberOfOpen+=i?1:0;
        }
        return numberOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolateFlag;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
    }
}
// https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html
// https://www.cnblogs.com/ikesnowy/p/7634797.html
// https://www.cnblogs.com/anne-vista/p/4841732.html
// https://blog.csdn.net/tengyuan93/article/details/77873252
