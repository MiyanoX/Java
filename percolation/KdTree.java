import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node head;
    private int size;
    private Point2D min;
    private List<Point2D> list;

    private class Node {
        Point2D p;
        Node left;
        Node right;
        boolean dir;  // if dir is true, compare x coordinate, else , compare y coordinate

        Node(Point2D p, Boolean dir) {
            this.p = p;
            this.dir = dir;
            this.left = null;
            this.right = null;
        }
    }

    public KdTree() {
        head = null;
        size = 0;
    }                             // construct an empty set of points

    public boolean isEmpty() {
        return head == null;
    }                      // is the set empty?

    public int size() {
        return size;
    }                        // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains null");
        if (isEmpty()) {
            head = new Node(p, true);
            size++;
            return;
        }
        Node cur = head;
        while (true) {
            if (cur.p.equals(p)) return;
            if ((cur.dir && p.x() < cur.p.x()) || (!cur.dir && p.y() < cur.p.y())) {
                if (cur.left == null) {
                    cur.left = new Node(p, !cur.dir);
                    size++;
                    return;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = new Node(p, !cur.dir);
                    size++;
                    return;
                } else {
                    cur = cur.right;
                }
            }
        }
    }            // add the point to the set (if it is not already in the set)
//
//    private boolean compare(Node cur, Point2D p) {
//        if (cur.dir) {
//            if (cur.p.x() > p.x()) return true;
//            else return false;
//        } else {
//            if (cur.p.y() > p.y()) return true;
//            else return false;
//        }
//    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains null");
        if (isEmpty()) {
            return false;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.p.equals(p)) {
                return true;
            }
            if ((cur.dir && p.x() < cur.p.x()) || (!cur.dir && p.y() < cur.p.y())) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }          // does the set contain point p?

    public void draw() {
        drawpoint(head);
    }                      // draw all points to standard draw

    private void drawpoint(Node cur) {
        if (cur == null) return;
        drawpoint(cur.left);
        drawpoint(cur.right);
        cur.p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rectangle is null");
        list = new ArrayList<>();
        Node cur = head;
        rangeOfRectangle(cur, rect);
        return list;
    }            // all points that are inside the rectangle (or on the boundary)

    private void rangeOfRectangle(Node cur, RectHV rect) {
        if (cur == null) return;
        if (rect.contains(cur.p)) {
            list.add(cur.p);
        }
        if ((cur.dir && rect.xmax() < cur.p.x()) || (!cur.dir && rect.ymax() < cur.p.y())) {
            rangeOfRectangle(cur.left, rect);
        } else if ((cur.dir && rect.xmin() > cur.p.x()) || (!cur.dir && rect.ymin() > cur.p.y())) {
            rangeOfRectangle(cur.right, rect);
        } else {
            rangeOfRectangle(cur.left, rect);
            rangeOfRectangle(cur.right, rect);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("contains null");
        if (isEmpty()) return null;
        min = head.p;
        RectHV rec = new RectHV(0, 0, 1, 1);
        Node cur = head;
        min(rec, cur, p);
        return min;

    }          // a nearest neighbor in the set to point p; null if the set is empty

    private void min(RectHV rec, Node cur, Point2D p) {
        if (cur == null) return;
//        System.out.println(cur.p.x());
        if (cur.p.distanceSquaredTo(p) < min.distanceSquaredTo(p)) min = cur.p;
        RectHV left, right;
        if (cur.dir) {
            left = new RectHV(rec.xmin(), rec.ymin(), cur.p.x(), rec.ymax());
            right = new RectHV(cur.p.x(), rec.ymin(), rec.xmax(), rec.ymax());
        } else {
            left = new RectHV(rec.xmin(), rec.ymin(), rec.xmax(), cur.p.y());
            right = new RectHV(rec.xmin(), cur.p.y(), rec.xmax(), rec.ymax());
        }
        if (left.contains(p)) {
            min(left, cur.left, p);
            if (right.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                min(right, cur.right, p);
            }
        } else if (right.contains(p)) {
            min(right, cur.right, p);
            if (left.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                min(left, cur.left, p);
            }
        } else {
            if (left.distanceSquaredTo(p) < min.distanceSquaredTo(p) && right.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                if (left.distanceSquaredTo(p) < right.distanceSquaredTo(p)) {
                    min(left, cur.left, p);
                    if (right.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                        min(right, cur.right, p);
                    }
                } else {
                    min(right, cur.right, p);
                    if (left.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                        min(left, cur.left, p);
                    }
                }
            } else if (left.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                min(left, cur.left, p);
            } else if (right.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
                min(right, cur.right, p);
            }
        }
    }


    public static void main(String[] args) {
//        KdTree kd = new KdTree();
//        System.out.println("start");
//        System.out.println(kd.isEmpty());
//        Point2D p1 = new Point2D(0.7, 0.2);
//        Point2D p2 = new Point2D(0.5, 0.4);
//        Point2D p3 = new Point2D(0.2, 0.3);
//        Point2D p4 = new Point2D(0.4, 0.7);
//        Point2D p5 = new Point2D(0.9, 0.6);
//        Point2D p6 = new Point2D(0.79, 0.49);
//        kd.insert(p1);
//        kd.insert(p2);
//        kd.insert(p3);
//        kd.insert(p4);
//        kd.insert(p5);
//        kd.nearest(p6);

    }
}
