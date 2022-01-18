package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    // You can assume points has at least size 1.
    public NaivePointSet(List<Point> points){
        // 把参数points赋值给内部参数points
        this.points = points;
    }

    @Override
    // Returns the closest point to the inputted coordinates.
    public Point nearest(double x, double y){
        Point targetPoint = new Point(x, y);

        Point nearestPoint = points.get(0);
        Double distance = Point.distance(targetPoint, nearestPoint);

        for (Point thePoint in points) {
            if (Point.distance(targetPoint, thePoint) < distance) {
                nearestPoint = thePoint;
            }
        }
        return nearestPoint;
    }
}
