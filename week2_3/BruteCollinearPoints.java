package week2_3;

import edu.princeton.cs.algs4.Merge;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int countSegment = 0;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(LineSegment.Point[] points) {
        if (checkNull(points)) throw new IllegalArgumentException();
        if (checkDuplicate(points)) throw new IllegalArgumentException();
        // use arraylist because unknown size
        ArrayList<LineSegment> arListSegments = new ArrayList<>();
        Arrays.sort(points);
        int n = points.length;
        for (int a = 0; a < n - 3; a += 1)
            for (int b = a + 1; b < n - 2; b += 1)
                for (int c = b + 1; c < n - 1; c += 1)
                    for (int d = c + 1; d < n; d += 1) {
                        LineSegment.Point p = points[a];
                        LineSegment.Point q = points[b];
                        LineSegment.Point r = points[c];
                        LineSegment.Point s = points[d];

                        if ((p.slopeTo(q) == p.slopeTo(r)) && (p.slopeTo(q) == p.slopeTo(s))) {
                            countSegment += 1;
                            arListSegments.add(new LineSegment(p, s));
                        }
                    }
        lineSegments = arListSegments.toArray(new LineSegment[0]);
    }

    private boolean checkDuplicate(LineSegment.Point[] points) {
        // copy and use merge sort to check
        LineSegment.Point[] points1 = new LineSegment.Point[points.length];
        System.arraycopy(points, 0, points1, 0, points1.length);

        Merge.sort(points1);
        for (int i = 0; i < points1.length - 1; i += 1) {
            if (points1[i] == points1[i + 1]) return true;
        }

        return false;
    }

    private boolean checkNull(LineSegment.Point[] points) {
        if (points == null) return true;
        for (int i = 0; i < points.length; i += 1) {
            if (points[i] == null) return true;
        }
        return false;
    }

    public int numberOfSegments() {
        return countSegment;
    } // the number of line segments

    public LineSegment[] segments() {
        return lineSegments;
    }// the line segments

}