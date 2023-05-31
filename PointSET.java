import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PointSET {
    private Set<Point2D> setOfPoints;

    public PointSET(Set<Point2D> setOfPoints) {
        if (setOfPoints == null) throw new IllegalArgumentException();
        this.setOfPoints = setOfPoints;
    }                              // construct an empty set of points

    public boolean isEmpty() {
        return setOfPoints.isEmpty();
    }                      // is the set empty?

    public int size() {
        return setOfPoints.size();
    }                       // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        setOfPoints.add(p);
    }             // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return setOfPoints.contains(p);
    }            // does the set contain point p?

    public void draw() {
        for (Point2D point : setOfPoints) {
            point.draw();
        }
    }                     // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> pointsInRec = new ArrayList<>();

        for (Point2D point : setOfPoints) {
            if (rect.contains(point)) {
                pointsInRec.add(point);
            }
        }
        return pointsInRec;
    }             // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        double min = 0;
        Point2D holder = null;

        for (Point2D point2D : setOfPoints) {
            if (min == 0 && holder == null) {
                min = point2D.distanceTo(p);
                holder = point2D;
                continue;
            }

            if (min >= point2D.distanceTo(p)) {
                min = point2D.distanceTo(p);
                holder = point2D;
            }
        }

        return holder;
    }        // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {

    }       // unit testing of the methods (optional)
}