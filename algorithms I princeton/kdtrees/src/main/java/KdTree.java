import java.util.Set;
import java.util.TreeSet;

public class KdTree {

    private Node root = null;
    private int size = 0;

    private static class Node {
        private Point2D point;
        private boolean compareHorizontally;
        private Node left = null;
        private Node right = null;

        public Node(Point2D point, boolean compareHorizontally) {
            this.point = point;
            this.compareHorizontally = compareHorizontally;
        }
    }

    public KdTree() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }

        if (contains(point)) {
            return;
        }

        if (root == null) {
            root = new Node(point, true);
        }
        else {
            insert(root, point);
        }
        size++;
    }

    private void insert(Node parent, Point2D point) {
        if (parent.compareHorizontally) {
            if (point.x() < parent.point.x()) {
                if (parent.left == null) {
                    parent.left = new Node(point, !parent.compareHorizontally);
                } else {
                    insert(parent.left, point);
                }
            } else if (parent.right == null) {
                parent.right = new Node(point, !parent.compareHorizontally);
            } else {
                insert(parent.right, point);
            }
        } else {
            if (point.y() < parent.point.y()) {
                if (parent.left == null) {
                    parent.left = new Node(point, !parent.compareHorizontally);
                } else {
                    insert(parent.left, point);
                }
            } else if (parent.right == null) {
                parent.right = new Node(point, !parent.compareHorizontally);
            } else {
                insert(parent.right, point);
            }
        }
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }

        return contains(root, point);
    }

    private boolean contains(Node parent, Point2D point) {
        if (parent == null) {
            return false;
        }
        if (parent.point.equals(point)) {
            return true;
        }
        if (parent.compareHorizontally) {
            if (point.x() < parent.point.x()) {
                return contains(parent.left, point);
            } else {
                return contains(parent.right, point);
            }
        } else {
            if (point.y() < parent.point.y()) {
                return contains(parent.left, point);
            } else {
                return contains(parent.right, point);
            }
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }
        node.point.draw();

        draw(node.left);
        draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }

        Set<Point2D> result = new TreeSet<>();
        range(root, result, rect);

        return result;
    }

    private void range(Node node, Set<Point2D> result, RectHV rect) {
        if (node == null) {
            return;
        }

        Point2D point = node.point;
        if (rect.contains(point)) {
            result.add(point);
        }

        if (node.compareHorizontally) {
            if (rect.xmax() < point.x()) {
                range(node.left, result, rect);
            } else if (rect.xmin() >= point.x()) {
                range(node.right, result, rect);
            } else {
                range(node.left, result, rect);
                range(node.right, result, rect);
            }
        } else {
            if (rect.ymax() < point.y()) {
                range(node.left, result, rect);
            } else if (rect.ymin() >= point.y()) {
                range(node.right, result, rect);
            } else {
                range(node.left, result, rect);
                range(node.right, result, rect);
            }
        }
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
