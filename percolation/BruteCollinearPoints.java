import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BruteCollinearPoints {
    private int number;
    private LineSegment[] line;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 point
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
        for (int p = 0; p < numberOfPoint - 3; p++) {
            for (int q = p + 1; q < numberOfPoint - 2; q++) {
                for (int r = q + 1; r < numberOfPoint - 1; r++) {
                    if (dup[p].slopeTo(dup[q]) != dup[p].slopeTo(dup[r])) continue;
                    for (int s = r + 1; s < numberOfPoint; s++) {
                        if (dup[p].slopeTo(dup[q]) == dup[p].slopeTo(dup[s])) {
                            number++;
                            LineSegment newline = new LineSegment(dup[p], dup[s]);
                            lineSegment.add(newline);
                        }
                    }
                }
            }
        }
        line = new LineSegment[number];
        for (int i = 0; i < number; i++) {
            line[i] = lineSegment.remove();
        }
    }

    public int numberOfSegments() {
        return number;
    }      // the number of line segments

    public LineSegment[] segments() {
        return line.clone();
    }            // the line segments
}
