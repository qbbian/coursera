import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * Created by bbian-chrome on 9/29/15.
 */
public class BruteCollinearPoints {
    private List<LineSegment> segs;
    private Point[] points;
    public BruteCollinearPoints(Point[] points) {
        // Error checkings
        if (points == null) throw new NullPointerException();
        for (Point point: points) {
            if (point == null) throw new NullPointerException();
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == points[j])
                    throw new IllegalArgumentException();
            }
        }
        this.points = points;
    }

    public int numberOfSegments() {
        return segs.size();
    }

    public LineSegment[] segments() {
        segs = new ArrayList<LineSegment>();
        // First sort points
        Arrays.sort(points);
        /*
        System.out.println("Sorted:");
        for (Point myPoint: points) {
            System.out.println(myPoint);
        }
        System.out.println();
        */
        List<Double> slopeList = new ArrayList<Double>();
        int min = 0;
        int max = points.length - 1;
        while (min != max) {
            for (int i = min; i < max; i++) {
                for (int j = max; j > i; j--) {
                    Point p = points[i];
                    Point q = points[j];
                    Double slope = p.slopeTo(q);
                    if (slopeList.contains(slope)) continue;
                    slopeList.add(slope);
                    segs.add(new LineSegment(p, q));
                }
            }
            min++;
        }

        LineSegment[] retVal = new LineSegment[segs.size()];
        retVal = segs.toArray(retVal);
        return retVal;
    }

    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
