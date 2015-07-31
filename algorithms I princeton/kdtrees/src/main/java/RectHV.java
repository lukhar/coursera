/*************************************************************************
 *  Compilation:  javac RectHV.java
 *  Execution:    java RectHV
 *  Dependencies: Point2D.java
 *
 *  Implementation of 2D axis-aligned rectangle.
 *
 *************************************************************************/

public final class RectHV {
    private final double xmin, ymin;   // minimum x- and y-coordinates
    private final double xmax, ymax;   // maximum x- and y-coordinates

    // construct the axis-aligned rectangle [xmin, xmax] x [ymin, ymax]
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        if (xmax < xmin || ymax < ymin) {
            throw new IllegalArgumentException("Invalid rectangle");
        }
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    // accessor methods for 4 coordinates
    public double xmin() {
        return xmin;
    }

    public double ymin() {
        return ymin;
    }

    public double xmax() {
        return xmax;
    }

    public double ymax() {
        return ymax;
    }

    // width and height of rectangle
    public double width() {
        return xmax - xmin;
    }

    public double height() {
        return ymax - ymin;
    }

    // does this axis-aligned rectangle intersect that one?
    public boolean intersects(RectHV that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
            && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }

    // draw this axis-aligned rectangle
    public void draw() {
        StdDraw.line(xmin, ymin, xmax, ymin);
        StdDraw.line(xmax, ymin, xmax, ymax);
        StdDraw.line(xmax, ymax, xmin, ymax);
        StdDraw.line(xmin, ymax, xmin, ymin);
    }
}