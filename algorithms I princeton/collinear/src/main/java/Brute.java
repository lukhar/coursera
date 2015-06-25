import java.util.Arrays;

public class Brute {

    private static final int MIN_SIZE = 4;

    public static void main(String[] args) {
        Point[] points = InputReader.read(args[0]);

        if (points.length < MIN_SIZE) {
            return;
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point point : points) {
            point.draw();
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k+1; l < points.length; l++) {
                        if (sameSlope(points[i], points[j], points[k], points[l])) {
                            print(points[i], points[j], points[k], points[l]);
                            draw(points[i], points[j], points[k], points[l]);
                        }
                    }
                }
            }
        }
    }

    private static void draw(Point... subset) {
        Arrays.sort(subset);
        subset[0].drawTo(subset[subset.length - 1]);
    }

    private static void print(Point... subset) {
        for (int i = 0; i < subset.length - 1; i++) {
            StdOut.print(subset[i] + " -> ");
        }
        StdOut.println(subset[subset.length - 1]);
    }

    private static boolean sameSlope(Point ref, Point... points) {
        double reference = ref.slopeTo(points[0]);
        for (int i = 0; i < points.length; i++) {
            if (ref.slopeTo(points[i]) != reference) {
                return false;
            }
        }

        return true;
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
