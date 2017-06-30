package week_3.homework;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {


    private final int x;                              // x coordinate  
    private final int y;                              // y coordinate


    public Comparator<Point> slopeOrder(){
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point>{

        public int compare(Point p1, Point p2) {
            double slopeP1 = slopeTo(p1);
            double slopeP2 = slopeTo(p2);
            if (slopeP1 == slopeP2) return 0;
            if (slopeP1 < slopeP2) return -1;
            else return +1;
        }

    }

    // create the point (x, y)  
    public Point(int x, int y) {  
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing  
    public void draw() {  
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing  
    public void drawTo(Point that) {  
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point  
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this.compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        else if (this.y == that.y)
            return +0;
        else
            return (that.y - this.y) * 1.0 / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?  
    // comparing y-coordinates and breaking ties by x-coordinates  
    public int compareTo(Point that) {  
        /* YOUR CODE HERE */
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;

    }

    // return string representation of this point  
    public String toString() {  
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test  
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode  
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger  

        // read in the input  
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();

        }

        // display to screen all at once  
        StdDraw.show(0);

        // reset the pen radius  
        StdDraw.setPenRadius();

    }
}  