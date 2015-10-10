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

    private final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
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

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
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

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return SLOPE_ORDER;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
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