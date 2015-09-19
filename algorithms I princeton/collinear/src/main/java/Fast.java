import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Fast {

    private static final int MIN_SIZE = 4;

    public static void main(String[] args) {
        Point[] points = Input.readPoints(args[0]);

        if (points.length < MIN_SIZE) {
            return;
        }

        List<List<Point>> collinear = new Fast().collinear(points);


        Output.printPoints(collinear);
        Output.drawPoints(points);
        Output.drawLines(collinear);
    }

    private List<List<Point>> collinear(Point[] points) {
        List<List<Point>> collinear = new ArrayList<>();
        Point[] ordered = Arrays.copyOf(points, points.length);
        Set<TreeSet<Point>> processedSequences = new HashSet<>();
        Arrays.sort(ordered);

        for (final Point ref : ordered) {
            Map<Double, TreeSet<Point>> pointsBySlope = new HashMap<>();
            Arrays.sort(points, ref.SLOPE_ORDER);

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

            for (TreeSet<Point> sortedPoints : pointsBySlope.values()) {
                if (sortedPoints.size() >= MIN_SIZE
                    && !processedSequences.contains(sortedPoints)) {
                    collinear.add(new ArrayList<>(sortedPoints));
                }
            }

            processedSequences.addAll(pointsBySlope.values());
        }

        return collinear;
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

        private static void printPoints(List<List<Point>> collinear) {
            for (List<Point> collinearPoints : collinear) {
                Iterator<Point> iterator = collinearPoints.iterator();
                while (iterator.hasNext()) {
                    Point point = iterator.next();
                    if (!iterator.hasNext()) {
                        StdOut.println(point);
                    } else {
                        StdOut.print(point + " -> ");
                    }
                }
            }
        }

        private static void drawLines(List<List<Point>> collinear) {
            for (List<Point> collinearPoints : collinear) {
                drawLine(collinearPoints);
            }
        }

        private static void drawLine(List<Point> collinearPoints) {
            collinearPoints.get(0).drawTo(
                collinearPoints.get(collinearPoints.size() - 1));
        }
    }
}
