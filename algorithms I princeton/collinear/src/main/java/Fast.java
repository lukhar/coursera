import sun.plugin.converter.util.StdUtils;

import java.util.Arrays;

public class Fast {
    private static final int MIN_SIZE = 4;
    private static int last = 0;
    private static Point[][] collinear;
    private static int lastCollinear = 0;

    public static void main(String[] args) {
        Point[] points = InputReader.read(args[0]);
        Point[] viewed = new Point[points.length];
        collinear = new Point[points.length][2];

        if (points.length < MIN_SIZE) {
            return;
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point point : points) {
            point.draw();
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point refPoint = choosePoint(points, viewed);

            Arrays.sort(points, refPoint.SLOPE_ORDER);

            double refSlope = refPoint.slopeTo(points[1]);
            int lo = 1;
            for (int hi = 1; hi < points.length; hi++) {
                double currentSlope = refPoint.slopeTo(points[hi]);
                if (currentSlope != refSlope || hi == points.length - 1) {
                    if (hi - lo >= MIN_SIZE - 1) {
                        Point[] subset = subset(refPoint, points, lo, hi);
                        if (!isDisplayed(subset)) {
                            print(subset);
                            draw(subset);
                            markAsDisplayed(subset);
                        }
                    }
                    lo = hi;
                    refSlope = currentSlope;
                }
            }
        }

    }

    private static boolean isDisplayed(Point[] subset) {
        double newSlope = subset[0].slopeTo(subset[1]);
        for (int i = 0; i < lastCollinear; i++) {
            Point first = collinear[i][0];
            Point last = collinear[i][1];
            if (first.slopeTo(last) == newSlope) {
                return subset[0].compareTo(last) <= 0 && subset[0].compareTo(first) >= 0;
            }
        }
        return false;
    }

    private static void markAsDisplayed(Point[] subset) {
        Arrays.sort(subset);
        Point[] cords = new Point[2];
        cords[0] = subset[0];
        cords[1] = subset[subset.length - 1];
        collinear[lastCollinear] = cords;
        lastCollinear++;
    }

    private static Point choosePoint(Point[] points, Point[] viewed) {
        Arrays.sort(viewed, 0, last);
        for (Point point : points) {
            if (Arrays.binarySearch(viewed, 0, last, point) < 0) {
                viewed[last] = point;
                last++;
                return point;
            }
        }

        return null;
    }

    private static void draw(Point[] subset) {
        Arrays.sort(subset);
        subset[0].drawTo(subset[subset.length -1]);
    }

    private static Point[] subset(Point refPoint, Point[] points, int lo, int hi) {
        Point[] subset =  new Point[hi - lo + 1];
        int j = 0;
        for (int i = lo; i < hi; i++) {
           subset[j] = points[i];
            j++;
        }
        subset[j] = refPoint;
        return subset;
    }

    private static void print(Point[] subset) {
        for (int i = 0; i < subset.length - 1; i++) {
            StdOut.print(subset[i] + " -> ");
        }
        StdOut.println(subset[subset.length - 1]);
    }

    private static class InputReader {

        private static Point[] read(String arg) {
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
}
