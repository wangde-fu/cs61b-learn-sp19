package bearmaps;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class KDTree implements PointSet {
    //divider line is vertical if splitDim is true
    private static final boolean HORIZONTAL = true;
    private Node root;             // root of KDTree

    private class Node {
        private Point thePoint;
        private boolean splitDim;
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
        // if (dimension) {
        Double middle = getTheDim(midPoint, dimension);
        for (Point thePoint : points) {
            // if (thePoint.getX() < middleX) {
            if (getTheDim(thePoint, dimension) < middle) {
                leftList.add(thePoint);
            }
            else if (getTheDim(thePoint, dimension) > middle || !thePoint.equals(midPoint)) {
                rightList.add(thePoint);
            }
        }

        Node theNode = new Node(midPoint, dimension);
        theNode.left = leftList.size() == 0? null:KDTree(leftList, !dimension);
        theNode.right = rightList.size() == 0? null:KDTree(rightList, !dimension);
        return theNode;
    }

    private Point middlePoint(List<Point> points, boolean splitDim){
        List<Double> theList = new ArrayList<>();
        Point midPoint = new Point(0, 0);
        // if (splitDim) {
        for (Point thePoint: points) {
            theList.add(getTheDim(thePoint, splitDim));
        }
        Collections.sort(theList);
        Double middlePos = theList.get(theList.size() / 2);
        for (Point thePoint: points) {
            if (getTheDim(thePoint, splitDim) == middlePos) {
                midPoint = thePoint;
            }
        }
        return midPoint;
    }

    private double getTheDim(Point thePoint, boolean splitDim){
        if (splitDim) {
            return thePoint.getX();
        }
        return thePoint.getY();
    }

    //Returns the closest point to the inputted coordinates.
    @Override
    public Point nearest(double x, double y){
        Node pSearch = root;
        Node nearestNode = new Node(new Point(0, 0), true);
        Node tempNode = new Node(new Point(0, 0), true);
        Point targetPoint = new Point(x, y);
        Double distance;
        // List<Point> searchNode = new ArrayList<>();
        Stack<Node> searchNode = new Stack<>();

        while (pSearch != null) {
            boolean pSearchDim = pSearch.getSplitDim();
            searchNode.push(pSearch);

            if (getTheDim(targetPoint, pSearchDim) < getTheDim(pSearch.getPoint(), pSearchDim)) {
                pSearch = pSearch.getLeft();
            }
            else if (getTheDim(targetPoint, pSearchDim) > getTheDim(pSearch.getPoint(), pSearchDim) || !targetPoint.equals(pSearch.getPoint())) {
                pSearch = pSearch.getRight();
            }
            else {
                // targetPoint.equals(pSearch.getPoint())
                return targetPoint;
            }
        }

        nearestNode = searchNode.pop();
        distance = Point.distance(nearestNode.getPoint(), targetPoint);

        while (!searchNode.empty()) {
            tempNode = searchNode.pop();

            // It's a leaf node
            if (tempNode.getLeft() == null && tempNode.getRight() == null) {
                if (Point.distance(tempNode.getPoint(), targetPoint) < Point.distance(nearestNode.getPoint(), targetPoint)) {
                    nearestNode = tempNode;
                    distance = Point.distance(tempNode.getPoint(), targetPoint);
                }
            }
            else{
                // 如果以target为中心的圆（球或超球），半径为dist的圆与分割超平面相交， 那么就要跳到另一边的子空间去搜索
                if (Math.abs(getTheDim(nearestNode.getPoint(), tempNode.getSplitDim()) - getTheDim(tempNode.getPoint(), tempNode.getSplitDim())) < distance) {
                    if (Point.distance(tempNode.getPoint(), targetPoint) < distance) {
                        nearestNode = tempNode;
                        distance = Point.distance(tempNode.getPoint(), targetPoint);
                    }
                    // 如果target位于tempNode的左子空间，那么就要跳到右子空间去搜索
                    if (getTheDim(targetPoint, tempNode.getSplitDim()) < getTheDim(tempNode.getPoint(), tempNode.getSplitDim())) {
                        pSearch = tempNode.getRight();
                    }else {
                        pSearch = tempNode.getLeft();
                    }
                    if (pSearch != null) {
                        searchNode.push(pSearch);
                    }
                }
            }
        }
        return nearestNode.getPoint();
    }

    public static void main(String[] args) {
        Point p1 = new Point(7, 2);
        Point p2 = new Point(9, 6);
        Point p3 = new Point(5, 4);
        Point p4 = new Point(4, 7);
        Point p5 = new Point(8, 1);
        Point p6 = new Point(2, 3);

        KDTree theTree = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point KDTreePoint = theTree.nearest(6.99, 0);
        System.out.println("Hi");
    }
}
