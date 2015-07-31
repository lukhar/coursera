public class KdTree {
    public KdTree() {
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return -1;
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
