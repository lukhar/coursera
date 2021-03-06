import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Set;
import java.util.TreeSet;

public class PointSET {
    private final Set<Point2D> points;

    public PointSET() {
        this.points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.size() == 0;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D point) {
        points.add(point);
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }

        return points.contains(point);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }

        TreeSet<Point2D> range = new TreeSet<>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                range.add(point);
            }
        }

        return range;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }

        Point2D nearestPoint = null;

        double minDistance = Double.MAX_VALUE;
        for (Point2D checkedPoint : points) {
            double distance = checkedPoint.distanceTo(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = checkedPoint;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
    }
}
