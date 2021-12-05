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
    private int numOpenSites = 0;
    // 为了防止backwash的问题，即只与底部相连的空间不应当有水，但是如果把底部和顶部加入并查集，就会导致这个问题。

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        this.N=N;
        // 这里connectTop并不能表示是否连接到顶部，仅仅是一个标识。每次“open”一个槽时，都将该槽与并查集的代表元素状态取或。
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

    private void validateRowCol(int row,int col){
        throw new IndexOutOfBoundsException("Index is not betwwen 1 and N");
    }
    // transform (row, col) to an array
    private int xy2array(int row,int col){
        validateRowCol(row,col);
        //top对应数组的0,bottom对应N*N+1
        return (row-1)*N+col-1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row,col);
        private int index=xy2array(row,col);
        // 这样可以减少赋值的次数
        private boolean top=false;
        private boolean bottom=false;

        // 若已经打开了这个格子，就直接返回
        if (isOpened[index]) {return;}

        numOpenSites++;
        if (row==1) {
            connectTop[index]==true;
        }
        if (row==N) {
            connectBottom[index]==true;
        }
        isOpened[index]=true;

        if (row>1&&isOpened[index-N]) {
            if (connectTop[uf.find(index)]||connectTop[uf.find(index-N)]) {
                top=true;
            }
            if (connectBottom[uf.find(index)]||connectBottom[uf.find(index-N)]) {
                bottom=true;
            }
            uf.union(index, index-N);
        }
        if (row<N&&isOpened[index+N]) {
            if (connectTop[uf.find(index)]||connectTop[uf.find(index+N)]) {
                top=true;
            }
            if (connectBottom[uf.find(index)]||connectBottom[uf.find(index+N)]) {
                bottom=true;
            }
            uf.union(index, index+N);
        }
        if (col>1&&isOpened[index-1]) {
            if (connectTop[uf.find(index)]||connectTop[uf.find(index-1)]) {
                top=true;
            }
            if (connectBottom[uf.find(index)]||connectBottom[uf.find(index-1)]) {
                bottom=true;
            }
            uf.union(index, index-1);
        }
        if (col<N&&isOpened[index+1]) {
            if (connectTop[uf.find(index)]||connectTop[uf.find(index+1)]) {
                top=true;
            }
            if (connectBottom[uf.find(index)]||connectBottom[uf.find(index+1)]) {
                bottom=true;
            }
            uf.union(index, index+1);
        }

        connectTop[uf.find(index)] = top;
        connectBottom[uf.find(index)] = bottom;
        if( connectTop[uf.find(index)] &&  connectBottom[uf.find(index)]) {
            percolateFlag = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row,col);
        return isOpened[xy2array(row,col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row,col);
        return connectTop[uf.find(xy2array(row,col))];
    }

    // number of open sites
    public int numberOfOpenSites() {
        /*int numberOfOpen=0;
        for (i : isOpened) {
            numberOfOpen+=i?1:0;
        }
        return numberOfOpen;*/
        // 题目要求这个函数调用固定时间
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolateFlag;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
    }
}
