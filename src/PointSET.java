import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> mPointsRBBst;
    // construct an empty set of points
    public PointSET() {
        mPointsRBBst = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return mPointsRBBst.isEmpty();
    }

    // number of points in the set
    public int size() {
        return mPointsRBBst.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException("The parameter cannot be null");
        }
        if(!mPointsRBBst.contains(p)){
            mPointsRBBst.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException("The parameter cannot be null");
        }
        return mPointsRBBst.contains(p);
    }

    // draw all points to standard draw
    public void draw() {

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);

        for (Point2D p : mPointsRBBst)
            StdDraw.point(p.x(), p.y());

        StdDraw.show();

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null){
            throw new IllegalArgumentException("The parameter cannot be null");
        }
        ArrayList<Point2D> points = new ArrayList<>();
        Iterator<Point2D> iterator = mPointsRBBst.iterator();
        while(iterator.hasNext()){
            Point2D next = iterator.next();
            if(rect.contains(next)){
                points.add(next);
            }
        }
        return points;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {

        if(p == null){
            throw new IllegalArgumentException("The parameter cannot be null");
        }

        if(mPointsRBBst == null){
            return null;
        }

        Point2D nearest = null;
        double nearestValue = Double.MAX_VALUE;

        Iterator<Point2D> iterator = mPointsRBBst.iterator();
        while(iterator.hasNext()){
            Point2D next = iterator.next();
            double v = p.distanceTo(next);
            if(v < nearestValue){
                nearestValue = v;
                nearest = next;
            }
        }

        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }


}
