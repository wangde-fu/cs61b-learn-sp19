package bearmaps;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class KDTree implements PointSet {
    //divider is in X dim if splitDim is true
    private static final boolean HORIZONTAL = true;
    private Node root;             // root of KDTree

    private class Node {
        private Point thePoint;
        private boolean splitDim;
        // private int size;          // number of nodes in subtree
        private Node left, right;  // left and right subtrees

        Node(Point point, boolean splitDim) {
            this.thePoint = point;
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
    public KDTree(List<Point> points){
        root = KDTree(points, HORIZONTAL);
    }
    private Node KDTree(List<Point> points, boolean dimension){
        Point midPoint = middlePoint(points, dimension);
        List<Point> leftList = new ArrayList<>();
        List<Point> rightList = new ArrayList<>();
        if (dimension) {
            Double middleX = midPoint.getX();
            for (Point thePoint : points) {
                if (thePoint.getX() < middleX) {
                    leftList.add(thePoint);
                }
                else if (thePoint.getX() > middleX || !thePoint.equals(midPoint)) {
                    rightList.add(thePoint);
                }
            }
        }else{
            Double middleY = midPoint.getY();
            for (Point thePoint : points) {
                if (thePoint.getY() < middleY) {
                    leftList.add(thePoint);
                }
                else if (thePoint.getY() > middleY || !thePoint.equals(midPoint)) {
                    rightList.add(thePoint);
                }
            }
        }

        Node theNode = new Node(midPoint, dimension);
        theNode.left = leftList.size() == 0? null:KDTree(leftList, !dimension);
        theNode.right = rightList.size() == 0? null:KDTree(rightList, !dimension);
        return theNode;
    }

    //Returns the closest point to the inputted coordinates.
    @Override
    public Point nearest(double x, double y){
        return new Point(0, 0);
    }

    private Point middlePoint(List<Point> points, boolean splitDim){
        List<Double> theList = new ArrayList<>();
        Point midPoint = new Point(0, 0);
        if (splitDim) {
            for (Point thePoint: points) {
                theList.add(thePoint.getX());
            }
            Collections.sort(theList);
            Double middle = theList.get(theList.size() / 2);
            for (Point thePoint: points) {
                if (thePoint.getX() == middle) {
                    midPoint = thePoint;
                }
            }
        }else{
            for (Point thePoint: points) {
                theList.add(thePoint.getY());
            }
            Collections.sort(theList);
            Double middle = theList.get(theList.size() / 2);
            for (Point thePoint: points) {
                if (thePoint.getY() == middle) {
                    midPoint = thePoint;
                }
            }
        }
        return midPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(7, 2);
        Point p2 = new Point(9, 6);
        Point p3 = new Point(5, 4);
        Point p4 = new Point(4, 7);
        Point p5 = new Point(8, 1);
        Point p6 = new Point(2, 3);

        KDTree theTree = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        System.out.println("Hi");
    }
}
