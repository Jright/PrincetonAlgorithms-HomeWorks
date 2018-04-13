import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private class Node {

        private Node left;
        private Node right;
        private final double x;
        private final double y;

        //Is this node placed according to Y-axis value or not.
        //Note: the children of rootNode is placed by X-axis, therefore the child node of rootNode isVertical = false
        private final boolean isVertical;

        public Node(double x, double y, Node left, Node right, boolean isVertical) {
            this.left = left;
            this.right = right;
            this.x = x;
            this.y = y;
            this.isVertical = isVertical;
        }
    }

    //    private static final RectHV
    private int size;
    private Node rootNode;

    // construct an empty set of points
    public KdTree() {
        size = 0;
        rootNode = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return rootNode == null;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The parameter cannot be null");
        }

        rootNode = insert(rootNode, p, true);
    }

    private Node insert(Node node, Point2D p, boolean isVertical) {
        if (node == null) {
            size++;
            return new Node(p.x(), p.y(), null, null, isVertical);
        }

        if (Double.compare(node.x, p.x()) == 0 && Double.compare(node.y, p.y()) == 0) {
            return node;
        }

        if (node.isVertical && node.x > p.x() || !node.isVertical && node.y > p.y()) {
            node.left = insert(node.left, p, !isVertical);
        } else {
            node.right = insert(node.right, p, !isVertical);
        }

        return node;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The parameter cannot be null");
        }
        return contains(rootNode, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null) {
            return false;
        }

        if (Double.compare(node.x, p.x()) == 0 && Double.compare(node.y, p.y()) == 0) {
            return true;
        }

        if (node.isVertical && node.x > p.x() || !node.isVertical && node.y > p.y()) {
            return contains(node.left, p);
        } else {
            return contains(node.right, p);
        }
    }

    private static final RectHV CONTAINER = new RectHV(0, 0, 1, 1);

    // draw all points to standard draw
    public void draw() {
        StdDraw.setScale(0, 1);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        CONTAINER.draw();

        draw(rootNode, CONTAINER);
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) {
            return;
        }

        // draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        new Point2D(node.x, node.y).draw();

        // get the min and max points of division line
        Point2D min, max;
        if (node.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            min = new Point2D(node.x, rect.ymin());
            max = new Point2D(node.x, rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            min = new Point2D(rect.xmin(), node.y);
            max = new Point2D(rect.xmax(), node.y);
        }

        // draw that division line
        StdDraw.setPenRadius();
        min.drawTo(max);

        // recursively draw children
        draw(node.left, leftRect(rect, node));
        draw(node.right, rightRect(rect, node));
    }

    private RectHV leftRect(RectHV rect, Node node) {
        if (node.isVertical) {
            return new RectHV(rect.xmin(), rect.ymin(), node.x, rect.ymax());
        } else {
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.y);
        }
    }

    private RectHV rightRect(RectHV rect, Node node) {
        if (node.isVertical) {
            return new RectHV(node.x, rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            return new RectHV(rect.xmin(), node.y, rect.xmax(), rect.ymax());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("The parameter cannot be null");
        }
        Stack<Point2D> insidePoints = new Stack<>();

        range(rect, CONTAINER, rootNode, insidePoints);

        return insidePoints;
    }

    private void range(RectHV targetRect, RectHV searchRect, Node node, Stack<Point2D> insidePoints) {
        if (node == null) {
            return;
        }

        if (targetRect.intersects(searchRect)) {
            Point2D point2D = new Point2D(node.x, node.y);

            if (targetRect.contains(point2D)) {
                insidePoints.push(point2D);
            }

            range(targetRect, leftRect(searchRect, node), node.left, insidePoints);
            range(targetRect, rightRect(searchRect, node), node.right, insidePoints);
        }

    }


    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The parameter cannot be null");
        }

        if(rootNode == null){
            throw new IllegalArgumentException("The parameter cannot be null");
        }

        Point2D rootNodePoint = new Point2D(rootNode.x, rootNode.y);

        return nearest(p, rootNode, rootNodePoint);
    }

    //TODO 没有通过测试
    /**
     * @param targetPoint The target point targetPoint
     * @param node        node, used to divide Rect, and calculate distance to targetPoint.
     */
    private Point2D nearest(Point2D targetPoint, Node node, Point2D currentNearestPoint) {

        Point2D nodePoint = new Point2D(node.x, node.y);

        if (Double.compare(node.x, targetPoint.x()) == 0 && Double.compare(node.y, targetPoint.y()) == 0) {
            return nodePoint;
        }

        if (Double.compare(currentNearestPoint.distanceSquaredTo(targetPoint), nodePoint.distanceSquaredTo(targetPoint)) < 0) {
            return currentNearestPoint;
        } else {
            currentNearestPoint = nodePoint;
        }

        if (node.left != null) {
            currentNearestPoint = nearest(targetPoint, node.left, currentNearestPoint);
        }
        if (node.right != null) {
            currentNearestPoint = nearest(targetPoint, node.right, currentNearestPoint);
        }

        return currentNearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }


}
