package bearmaps;

public interface PointSet {
    // Returns the point in the set nearest to x, y.
    Point nearest(double x, double y);
}
