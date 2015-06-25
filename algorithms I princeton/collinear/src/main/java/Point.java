/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private static final double POSITIVE_ZERO = 0.0;
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double diff = slopeTo(o1) - slopeTo(o2);
            return ceil(diff);
        }
    };

    private int ceil(double diff) {
        if (diff > 0) return 1;
        if (diff < 0) return -1;
        return 0;
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
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

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        return this.y - that.y == 0
                ? this.x - that.x
                : this.y - that.y;
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