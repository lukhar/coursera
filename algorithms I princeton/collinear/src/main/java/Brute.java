import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Brute {

    private static final int MIN_SIZE = 4;

    public static void main(String[] args) {
        Point[] points = Input.readPoints(args[0]);

        if (points.length < MIN_SIZE) {
            return;
        }

        List<List<Point>> collinear = new Brute().collinear(points);


        Output.printPoints(collinear);
        Output.drawPoints(points);
        Output.drawLines(collinear);
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

    private List<List<Point>> collinear(Point[] points) {
        List<List<Point>> collinear = new ArrayList<>();


        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (sameSlope(points[i], points[j], points[k], points[l])) {
                            collinear.add(
                                Arrays.asList(
                                    points[i], points[j], points[k], points[l]));
                        }
                    }
                }
            }
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
