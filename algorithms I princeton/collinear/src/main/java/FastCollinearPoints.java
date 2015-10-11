import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class FastCollinearPoints {

    private static final int MIN_SIZE = 4;
    private final Point[] points;
    private int numberOfSegments;

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
        List<LineSegment> segments = new ArrayList<>();
        Point[] ordered = Arrays.copyOf(points, points.length);
        Set<Double> processedSlopes = new TreeSet<>();

        Arrays.sort(ordered);

        for (final Point ref : ordered) {
            Map<Double, TreeSet<Point>> pointsBySlope = new HashMap<>();
            Arrays.sort(points, ref.slopeOrder());

            for (final Point point : points) {
                if (point.equals(ref)) {
                    continue;
                }

                double slope = ref.slopeTo(point);

                if (pointsBySlope.containsKey(slope)) {
                    pointsBySlope.get(slope).add(point);
                } else {
                    TreeSet<Point> sameSlopePoints = new TreeSet<>();
                    sameSlopePoints.add(ref);
                    sameSlopePoints.add(point);
                    pointsBySlope.put(slope, sameSlopePoints);
                }
            }

            for (Map.Entry<Double, TreeSet<Point>> slopeAndPoints :
                pointsBySlope.entrySet()) {

                if (slopeAndPoints.getValue().size() >= MIN_SIZE
                    && !processedSlopes.contains(slopeAndPoints.getKey())) {
                    segments.add(new LineSegment(
                        slopeAndPoints.getValue().first(),
                        slopeAndPoints.getValue().last()));
                }
            }

            processedSlopes.addAll(pointsBySlope.keySet());
        }

        this.numberOfSegments = segments.size();

        return segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
        return numberOfSegments;
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
