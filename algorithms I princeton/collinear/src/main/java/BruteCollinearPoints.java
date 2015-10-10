import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private static final int MIN_SIZE = 4;

    private final Point[] points;
    private List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        if (hasDuplicates(points)) {
            throw new IllegalArgumentException();
        }

        this.points = points.clone();
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

    private boolean hasDuplicates(Point[] elements) {
        Arrays.sort(elements);
        for (int i = 1; i < elements.length; i++) {
            if (elements[i - 1].compareTo(elements[i]) == 0) {
                return true;
            }
        }

        return false;
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
        segments = new ArrayList<>();

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (sameSlope(points[i], points[j], points[k], points[l])) {
                            segments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }

        return segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
       return segments.size();
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

        static {
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
        }

        private static void drawPoints(Point... points) {
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
            for (LineSegment segment : collinear) {
                segment.draw();
            }
        }
    }
}
