package week2_3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

import static edu.princeton.cs.algs4.QuickBentleyMcIlroy.exch;
import static edu.princeton.cs.algs4.QuickBentleyMcIlroy.less;

/*************************************************************************
 *  Compilation:  javac LineSegment.java
 *  Execution:    none
 *  Dependencies: Point.java
 *
 *  An immutable data type for Line segments in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 *  DO NOT MODIFY THIS CODE.
 *
 *************************************************************************/

public class LineSegment {
    private final Point p;   // one endpoint of this line segment
    private final Point q;   // the other endpoint of this line segment

    /**
     * Initializes a new line segment.
     *
     * @param p one endpoint
     * @param q the other endpoint
     * @throws NullPointerException if either <tt>p</tt> or <tt>q</tt>
     *                              is <tt>null</tt>
     */
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("argument to LineSegment constructor is null");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("both arguments to LineSegment constructor are the same point: " + p);
        }
        this.p = p;
        this.q = q;
    }


    /**
     * Draws this line segment to standard draw.
     */
    public void draw() {
        p.drawTo(q);
    }

    /**
     * Returns a string representation of this line segment
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this line segment
     */
    public String toString() {
        return p + " -> " + q;
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * assignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported");
    }

    public static class Point implements Comparable<Point> {

        private final int x;     // x-coordinate of this point
        private final int y;     // y-coordinate of this point

        /**
         * Initializes a new point.
         *
         * @param x the <em>x</em>-coordinate of the point
         * @param y the <em>y</em>-coordinate of the point
         */
        public Point(int x, int y) {
            /* DO NOT MODIFY */
            this.x = x;
            this.y = y;
        }

        /**
         * Draws this point to standard draw.
         */
        public void draw() {
            /* DO NOT MODIFY */
            StdDraw.point(x, y);
        }

        /**
         * Draws the line segment between this point and the specified point
         * to standard draw.
         *
         * @param that the other point
         */
        public void drawTo(Point that) {
            /* DO NOT MODIFY */
            StdDraw.line(this.x, this.y, that.x, that.y);
        }

        /**
         * Returns the slope between this point and the specified point.
         * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
         * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
         * +0.0 if the line segment connecting the two points is horizontal;
         * Double.POSITIVE_INFINITY if the line segment is vertical;
         * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
         *
         * @param that the other point
         * @return the slope between this point and the specified point
         */
        public double slopeTo(Point that) {
            // equal
            if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
            // same x -> vertical
            if (this.x == that.x) return Double.POSITIVE_INFINITY;
            // same y -> horizontal
            if (this.y == that.y) return 0;
            // two different points
            return ((double) that.y - this.y) / ((double) that.x - this.x);
        }

        /**
         * Compares two points by y-coordinate, breaking ties by x-coordinate.
         * Formally, the invoking point (x0, y0) is less than the argument point
         * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
         *
         * @param that the other point
         * @return the value <tt>0</tt> if this point is equal to the argument
         * point (x0 = x1 and y0 = y1);
         * a negative integer if this point is less than the argument
         * point; and a positive integer if this point is greater than the
         * argument point
         */
        public int compareTo(Point that) {
            // equal
            if (this.x == that.x && this.y == that.y) return 0;
            // less
            if (this.y < that.y) return -1;
            if (this.y == that.y && this.x < that.x) return -1;
            // greater
            return 1;
        }

        /**
         * Compares two points by the slope they make with this point.
         * The slope is defined as in the slopeTo() method.
         *
         * @return the Comparator that defines this ordering on points
         */
        public Comparator<Point> slopeOrder() {
            return new Comparator<Point>() {
                @Override
                public int compare(Point point1, Point point2) {
                    double slope1 = slopeTo(point1);
                    double slope2 = slopeTo(point2);
                    return Double.compare(slope1, slope2);
                }
            };
        }

        /**
         * Returns a string representation of this point.
         * This method is provide for debugging;
         * your program should not rely on the format of the string representation.
         *
         * @return a string representation of this point
         */
        public String toString() {
            /* DO NOT MODIFY */
            return "(" + x + ", " + y + ")";
        }

        /**
         * Unit tests the Point data type.
         */
        public static void main(String[] args) {
            Point pRoot = new Point(1, 1);
            Point p1 = new Point(1, 2);
            Point p2 = new Point(3, 2);
            System.out.println(pRoot.slopeTo(p1));
            System.out.println(pRoot.slopeTo(p2));
        }
    }

    public static class QuickSortEx {
        private static int partition(Comparable[] a, int lo, int hi) {
            int i = lo, j = hi + 1;
            while (true) {
                while (less(a[++i], a[lo]))
                    if (i == hi) break;
                while (less(a[lo], a[--j]))
                    if (j == lo) break;

                if (i >= j) break;
                exch(a, i, j);
            }

            exch(a, lo, j);
            return j;
        }

        private static Comparable select(Comparable[] a, int k) {
            StdRandom.shuffle(a);
            int lo = 0, hi = a.length - 1;
            while (hi > lo) {
                int j = partition(a, lo, hi);
                if (j < k) lo = j + 1;
                else if (j > k) hi = j - 1;
                else return a[k];
            }

            return a[k];
        }

        public static void main(String[] args) {
            Comparable[] a = {2, 3, 5, 6, 1, 4};
            //2,3,4,5,6,8
            //System.out.println(partition(a, 0, a.length - 1));
            System.out.println(select(a, 2));

            for (int i = 0; i < a.length; i += 1) {
                System.out.print(a[i] + "  ");
            }
        }
    }
}
