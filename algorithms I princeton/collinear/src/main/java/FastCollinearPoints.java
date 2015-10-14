import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.geom.Arc2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class FastCollinearPoints {

    private static final int MIN_SIZE = 4;
    private final Point[] points;
    private int numberOfLineSegments;

    public FastCollinearPoints(Point[] points) {
        Arrays.sort(points);

        if (hasDuplicates(points)) {
            throw new IllegalArgumentException();
        }

        this.points = points.clone();
    }

    private boolean hasDuplicates(Point[] elements) {
        for (int i = 1; i < elements.length; i++) {
            if (elements[i - 1].compareTo(elements[i]) == 0) {
                return true;
            }
        }

        return false;
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
        Point[] ordered = Arrays.copyOf(points, points.length);
        TreeMap<Point, TreeSet<Point>> segments = new TreeMap<>();
        List<LineSegment> lineSegments = new ArrayList<>();

        Arrays.sort(ordered);

        for (final Point ref : ordered) {
            Arrays.sort(points, ref.slopeOrder());
            TreeSet<Point> currentSegment = new TreeSet<>();
            double previousSlope = Double.MAX_VALUE;

            for (final Point point : points) {
                if (point.equals(ref)) {
                    continue;
                }

                double slope = ref.slopeTo(point);

                if (slope != previousSlope) {
                    if (currentSegment.size() >= MIN_SIZE) {
                        Point first = currentSegment.first();
                        Point last = currentSegment.last();

                        if (segments.containsKey(first)
                            && !segments.get(first).contains(last)) {
                            segments.get(first).add(last);
                        }
                        else if (!segments.containsKey(first)) {
                            TreeSet<Point> temp = new TreeSet<>();
                            temp.add(last);
                            segments.put(first, temp);
                        }
                    }
                    currentSegment = new TreeSet<>();
                    currentSegment.add(ref);
                }

                currentSegment.add(point);
                previousSlope = slope;
            }

            if (currentSegment.size() >= MIN_SIZE) {
                Point first = currentSegment.first();
                Point last = currentSegment.last();

                if (segments.containsKey(first)
                    && !segments.get(first).contains(last)) {
                    segments.get(first).add(last);
                }
                else if (!segments.containsKey(first)) {
                    TreeSet<Point> temp = new TreeSet<>();
                    temp.add(last);
                    segments.put(first, temp);
                }
            }
        }

        for (Map.Entry<Point, TreeSet<Point>> segmentsByPoint
            : segments.entrySet()) {
            Point first = segmentsByPoint.getKey();
            for (Point last : segmentsByPoint.getValue()) {
                lineSegments.add(new LineSegment(first, last));
            }
        }

        this.numberOfLineSegments = lineSegments.size();

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public int numberOfSegments() {
        return numberOfLineSegments;
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
