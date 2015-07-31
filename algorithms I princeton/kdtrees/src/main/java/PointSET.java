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
        if (point == null) {
            throw new NullPointerException();
        }
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }

        return false;
    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }

        return null;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }

        return null;
    }

    public static void main(String[] args) {
    }
}
