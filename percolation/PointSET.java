import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.List;

public class PointSET {
    private SET<Point2D> pointSet;

    public PointSET() {
        pointSet = new SET<>();
    }                              // construct an empty set of points

    public boolean isEmpty() {
        return pointSet.isEmpty();
    }                     // is the set empty?

    public int size() {
        return pointSet.size();
    }                   // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("insert null");
        pointSet.add(p);
    }             // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains null");
        return pointSet.contains(p);
    }           // does the set contain point p?

    public void draw() {
        for (Point2D i : pointSet) {
            i.draw();
        }
    }                      // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rectangle is null");
        List<Point2D> list = new ArrayList<>();
        for (Point2D i : pointSet) {
            if (rect.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }            // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("point is null");
        if (isEmpty()) return null;
        Point2D near = p;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point2D i : pointSet) {
            if (i.distanceSquaredTo(p) < minDistance) {
                minDistance = i.distanceSquaredTo(p);
                near = i;
            }
        }
        return near;
    }          // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        // unit testing of the methods (optional)
    }
}
