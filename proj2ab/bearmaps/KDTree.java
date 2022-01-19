package bearmaps;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Collections;

public class KDTree implements PointSet {
    private static Double error = 0.00000001;
    private Node root;             // root of KDTree

    private class Node {
        private Point thpoint;
        private boolean splitDim;
        // private int size;          // number of nodes in subtree
        private Node left, right;  // left and right subtrees

        Node(Point point, boolean splitDim) {
            this.point = point;
            this.splitDim = splitDim;
            left = null;
            right = null;
        }

        public Point getPoint() { return thePoint; }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
        public boolean getSplitDim() { return splitDim; }
    }

    // You can assume points has at least size 1.
    public KDTree(List<Point> points){}

    @Override
    // Returns the closest point to the inputted coordinates.
    public Point nearest(double x, double y){}

    // public static void main(String[] args) {
    //     Point p1 = new point(7, 2);
    //     Point p2 = new point(9, 6);
    //     Point p3 = new point(5, 4);
    //     Point p4 = new point(4, 7);
    //     Point p5 = new point(8, 1);
    //     Point p6 = new point(2, 3);
    // }
}
