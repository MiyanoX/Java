import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FastCollinearPoints {
    private int number;
    private LineSegment[] line;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points == null");

        int numberOfPoint = points.length;
        for (int i = 0; i < numberOfPoint; i++) {
            if (points[i] == null) throw new IllegalArgumentException("points[" + i + "] == null");
        }
        Point[] dup = points.clone();
        Arrays.sort(dup);
        for (int i = 0; i < numberOfPoint - 1; i++) {
            if (dup[i] == dup[i + 1])
                throw new IllegalArgumentException("the argument to the constructor contains a repeated point.");
        }

        number = 0;
        Queue<LineSegment> lineSegment = new LinkedList<LineSegment>();
        for (int i = 0; i < numberOfPoint; i++) {
            Arrays.sort(dup, points[i].slopeOrder());

            int head = 1;
            int tail = head + 1;
            while (tail < numberOfPoint) {

                double headslope = points[i].slopeTo(dup[head]);

                while (tail < numberOfPoint && headslope == points[i].slopeTo(dup[tail])) {
                    tail++;
                }

                if (tail - head >= 3) {
                    boolean flag = true;
                    Point max = points[i];
                    for (int j = head; j < tail; j++) {
                        if (points[i].compareTo(dup[j]) > 0) {
                            flag = false;
                            break;
                        }
                        if (dup[j].compareTo(max) > 0) {
                            max = dup[j];
                        }
                    }

                    if (flag) {  // judge that push this line segment into queue or not
                        number++;
                        LineSegment newline = new LineSegment(points[i], max);
                        lineSegment.add(newline);
                    }
                }

                head = tail;
                tail++;
            }
        }
        line = new LineSegment[number];
        for (int i = 0; i < number; i++) {
            line[i] = lineSegment.remove();
        }

    }   // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return number;
    }      // the number of line segments

    public LineSegment[] segments() {
        return line.clone();
    }            // the line segments              // the line segments
}
