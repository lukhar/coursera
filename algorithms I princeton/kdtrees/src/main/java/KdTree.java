public class KdTree {

    private Node root;

    private static class Node {
        private Point2D point;
        private boolean compareX;
        private Node left = null;
        private Node right = null;

        public Node(Point2D point, boolean compareX) {
            this.point = point;
            this.compareX = compareX;
        }
    }

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

        if (root == null) {
            root = new Node(point, true);
        }

        insert(root, point);
    }

    private void insert(Node parent, Point2D point) {
        if (parent.compareX) {
            if (point.x() < parent.point.x()) {
                if (parent.left == null) {
                    parent.left = new Node(point, !parent.compareX);
                } else {
                    insert(parent.left, point);
                }
            } else if (parent.right == null) {
                parent.right = new Node(point, !parent.compareX);
            } else {
                insert(parent.right, point);
            }
        } else {
            if (point.y() < parent.point.y()) {
                if (parent.left == null) {
                    parent.left = new Node(point, !parent.compareX);
                } else {
                    insert(parent.left, point);
                }
            } else if (parent.right == null) {
                parent.right = new Node(point, !parent.compareX);
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
        if (parent.compareX) {
            if (point.x() < parent.point.x()) {
                return contains(parent.left, point);
            }
            else {
                return contains(parent.right, point);
            }
        } else {
            if (point.y() < parent.point.y()) {
                return contains(parent.left, point);
            }
            else {
                return contains(parent.right, point);
            }
        }
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
