import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jeremy on 17年6月30日.
 */
public class FastCollinearPoints {


//    FastCollinearPoints:
//    The following constructors are missing:
//            * public FastCollinearPoints(Point[])
//
//    The following constructors must either be removed or made private:
//            * public FastCollinearPoints()
//
//    The following methods are missing:
//            * public LineSegment[] segments()
//  * public int numberOfSegments()


    public static void main(String[] args){
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        if (N < 4){
            return;
        }
        Point[] pointArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pointArray[i] = p;
            p.draw();
        }
        Quick3way.sort(pointArray);
        FastMethod(pointArray);
        StdDraw.show(0);
    }

    private static void FastMethod(Point[] pointArray){
        int N = pointArray.length;
        for (int i=0; i<N; i++){
            Point origPoint = pointArray[i];
            Point[] otherPoint = new Point[N-1];
            for (int j=0; j<pointArray.length; j++){
                if (j < i) otherPoint[j] = pointArray[j];
                if (j > i) otherPoint[j-1] = pointArray[j];
            }

            Arrays.sort(otherPoint, new Point.SlopeOrder());

            int count = 0;
            int index = 0;
            double tempSlope = origPoint.slopeTo(otherPoint[0]);
            for (int j=0; j<otherPoint.length;j++){
                if (Double.compare(origPoint.slopeTo(otherPoint[j]),  tempSlope) == 0){
                    count++;
                    continue;
                }else{
                    if (count >=3){
                        if (otherPoint[index].compareTo(origPoint) >=0){
                            StdOut.print(origPoint + " -> ");
                            for (int k=index; k<j-1; k++){
                                StdOut.print(otherPoint[k] + " -> ");
                            }
                            StdOut.println(otherPoint[j-1]);
                            origPoint.drawTo(otherPoint[j-1]);
                        }
                    }
                    count = 1;
                    index = j;
                    tempSlope = origPoint.slopeTo(otherPoint[j]);
                }
            }
            if (count >= 3){
                if (otherPoint[index].compareTo(origPoint) >= 0){
                    StdOut.print(origPoint + " -> ");
                    for (int k=index; k<otherPoint.length - 1; k++){
                        StdOut.print(otherPoint[k] + " -> ");
                    }
                    StdOut.println(otherPoint[otherPoint.length-1]);
                    origPoint.drawTo(otherPoint[otherPoint.length-1]);
                }
            }
        }
        StdDraw.show(0);
    }

}
