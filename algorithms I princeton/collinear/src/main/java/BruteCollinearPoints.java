import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class BruteCollinearPoints {

    private static final int MIN_SIZE = 4;

    private final Point[] points;
    private List<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (hasDuplicates(points)) {
            throw new IllegalArgumentException();
        }
        this.points = Arrays.copyOf(points, points.length);
    }

    public static void main(String[] args) {
        Point[] points = Input.readPoints(args[0]);

        if (points.length < MIN_SIZE) {
            return;
        }

        LineSegment[] collinear = new BruteCollinearPoints(points).segments();

        Output.printPoints(collinear);
        Output.drawPoints(points);
        Output.drawLines(collinear);
    }

    private static boolean hasDuplicates(Point[] elements) {
        return new TreeSet<>(Arrays.asList(elements)).size() != elements.length;
    }

    private static boolean sameSlope(Point ref, Point... points) {
        if (points == null || points.length == 0) {
            return true;
        }
        double reference = ref.slopeTo(points[0]);

        for (Point point : points) {
            if (ref.slopeTo(point) != reference) {
                return false;
            }
        }

        return true;
    }

    public LineSegment[] segments() {
        if (lineSegments != null) {
            lineSegments.toArray(new LineSegment[lineSegments.size()]);
        }

        lineSegments = new ArrayList<>();

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (sameSlope(points[i], points[j], points[k], points[l])) {
                            lineSegments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public int numberOfSegments() {
        if (lineSegments == null) {
            segments();
        }
        return lineSegments.size();
    }

    private static class Input {

        private static Point[] readPoints(String arg) {
            In in = new In(arg);
            int numberOfPoints = in.readInt();

            Point[] points = new Point[numberOfPoints];
            for (int index = 0; index < numberOfPoints; index++) {
                int x = in.readInt();
                int y = in.readInt();
                points[index] = new Point(x, y);
            }

            return points;
        }
    }

    private static class Output {

        private static void setScale() {
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
        }

        private static void drawPoints(Point... points) {
            setScale();
            for (Point point : points) {
                point.draw();
            }
        }

        private static void printPoints(LineSegment[] collinear) {
            for (LineSegment segment : collinear) {
                StdOut.println(segment);
            }
        }

        private static void drawLines(LineSegment[] collinear) {
            setScale();
            for (LineSegment segment : collinear) {
                segment.draw();
            }
        }
    }
}
