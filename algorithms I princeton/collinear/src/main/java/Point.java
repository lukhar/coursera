/*************************************************************************
 * Name:
 * Email:
 * <p/>
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 * <p/>
 * Description: An immutable data type for points in the plane.
 *************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private static final double POSITIVE_ZERO = 0.0;

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double diff = slopeTo(o1) - slopeTo(o2);
            return ceil(diff);
        }
    };

    private final int x;
    private final int y;

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    private static int ceil(double diff) {
        if (diff > 0) return 1;
        if (diff < 0) return -1;
        return 0;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        int dx = that.y - this.y;
        int dy = that.x - this.x;

        if (dx == 0 && dy == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (dy == 0) {
            return Double.POSITIVE_INFINITY;
        } else if (dx == 0) {
            return POSITIVE_ZERO;
        }

        return (double) dx / (double) dy;
    }

    public Comparator<Point> slopeOrder() {
        return SLOPE_ORDER;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        boolean yCoordinatesEqual = this.y - that.y == 0;

        if (yCoordinatesEqual) {
            return this.x - that.x;
        }

        return this.y - that.y;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}