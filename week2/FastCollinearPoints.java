package week2;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (checkNull(points)) throw new IllegalArgumentException();
        if (checkDuplicate(points)) throw new IllegalArgumentException();


        // arrayList for lineSegment
        ArrayList<LineSegment> arListSegments = new ArrayList<>();

        // sort the points first
        Point[] pointsSortedByPoint = new Point[points.length];
        System.arraycopy(points, 0, pointsSortedByPoint, 0, pointsSortedByPoint.length);
        Arrays.sort(pointsSortedByPoint);

        // declare sort by slope array
        Point[] pointsSortedBySlope = new Point[points.length];

        // treat point p as origin
        for (Point p : pointsSortedByPoint) {
            System.arraycopy(pointsSortedByPoint, 0, pointsSortedBySlope, 0, pointsSortedBySlope.length);
            Arrays.sort(pointsSortedBySlope, p.slopeOrder());

            // index to run other
            int index = 1; // 0 is the origin vs origin

            while (index < pointsSortedBySlope.length) {
                // currentPoints is collection of points that are processing
                ArrayList<Point> currentPoints = new ArrayList<>();
                double currentSlope = p.slopeTo(pointsSortedBySlope[index]);
                currentPoints.add(pointsSortedBySlope[index]);
                // run loop to check next points
                index += 1;
                while (index < pointsSortedBySlope.length && p.slopeTo(pointsSortedBySlope[index]) == currentSlope) {
                    currentPoints.add(pointsSortedBySlope[index]);
                    index += 1;
                }
                
                // check if we have more than 3 points that are in the same segment with p (origin)
                // check compare to make sure there will be no duplicate
                if (currentPoints.size() >= 3 && p.compareTo(currentPoints.get(0)) < 0) {
                    arListSegments.add(new LineSegment(p, currentPoints.get(currentPoints.size() - 1)));
                }
            }
        }

        // convert arraylist to array
        lineSegments = arListSegments.toArray(new LineSegment[0]);
    }    // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return lineSegments.length;
    }   // the number of line segments

    public LineSegment[] segments() {
        return lineSegments;
    }  // the line segments

    private boolean checkDuplicate(Point[] points) {
        // copy and use merge sort to check
        Point[] points1 = new Point[points.length];
        System.arraycopy(points, 0, points1, 0, points1.length);

        Merge.sort(points1);
        for (int i = 0; i < points1.length - 1; i += 1) {
            if (points1[i].compareTo(points1[i + 1]) == 0) return true;
        }

        return false;
    }

    private boolean checkNull(Point[] points) {
        if (points == null) return true;
        for (int i = 0; i < points.length; i += 1) {
            if (points[i] == null) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}