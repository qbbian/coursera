import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;
import java.util.*;

/**
 * Created by bbian-chrome on 9/30/15.
 */
public class FastCollinearPoints {
    private HashMap<Double, LineSegment> segs;
    private Point[] points;
    public FastCollinearPoints(Point[] points) {
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
        segs = new HashMap<Double, LineSegment>();
        Arrays.sort(points);

        for (Point p: points) {
            for (Point q: points) {
                if (p == q) continue;
                Double slope = new Double(p.slopeTo(q));
                LineSegment oldSeg = segs.get(slope);
                if (oldSeg == null) {
                    segs.put(slope, new LineSegment(p, q));
                }
            }
        }

        List<LineSegment> segList = new ArrayList<LineSegment>(segs.values());
        LineSegment[] retVal = new LineSegment[segList.size()];
        retVal = segList.toArray(retVal);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
