import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class FastCollinearPoints {

    private static final int MIN_SIZE = 4;
    private final Point[] points;
    private List<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (hasDuplicates(points)) {
            throw new IllegalArgumentException();
        }
        this.points = Arrays.copyOf(points, points.length);
    }

    private static boolean hasDuplicates(Point[] elements) {
        return new TreeSet<>(Arrays.asList(elements)).size() != elements.length;
    }

    public static void main(String[] args) {
        Point[] points = Input.readPoints(args[0]);

        if (points.length < MIN_SIZE) {
            return;
        }

        LineSegment[] collinear = new FastCollinearPoints(points).segments();

        Output.printPoints(collinear);
        Output.drawPoints(points);
        Output.drawLines(collinear);
    }

    public LineSegment[] segments() {
        if (lineSegments != null) {
            return lineSegments.toArray(new LineSegment[lineSegments.size()]);
        }

        Point[] reordered = Arrays.copyOf(points, points.length);
        TreeMap<Point, TreeSet<Point>> segments = new TreeMap<>();
        lineSegments = new ArrayList<>();

        for (final Point ref : this.points) {
            Arrays.sort(reordered, ref.slopeOrder());
            int currentSegmentSize = 1;
            double previousSlope = Double.MAX_VALUE;
            Point first = ref;
            Point last = ref;

            for (int i = 1; i < reordered.length; i++) {
                Point point = reordered[i];

                double slope = ref.slopeTo(point);

                if (slope != previousSlope) {
                    if (currentSegmentSize >= MIN_SIZE) {

                        segments.putIfAbsent(first,
                            new TreeSet<>(Arrays.asList(last)));
                        segments.get(first).add(last);
                    }
                    first = ref;
                    last = ref;
                    currentSegmentSize = 1;
                }

                if (smaller(first, point)) {
                    first = point;
                }
                else if (larger(last, point)) {
                    last = point;
                }

                currentSegmentSize += 1;
                previousSlope = slope;
            }

            if (currentSegmentSize >= MIN_SIZE) {

                segments.putIfAbsent(first,
                    new TreeSet<>(Arrays.asList(last)));
                segments.get(first).add(last);
            }
        }

        for (Map.Entry<Point, TreeSet<Point>> segmentsByPoint
            : segments.entrySet()) {
            Point first = segmentsByPoint.getKey();
            for (Point last : segmentsByPoint.getValue()) {
                lineSegments.add(new LineSegment(first, last));
            }
        }

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private static boolean larger(Point last, Point point) {
        return point.compareTo(last) > 0;
    }

    private static boolean smaller(Point first, Point point) {
        return point.compareTo(first) < 0;
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
