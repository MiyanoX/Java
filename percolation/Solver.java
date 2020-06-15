import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {
    private int moves;
    private boolean solvable;
    private Stack<Board> solution;

    private static class Node {
        Board board;
        Node prev;
        int moves;
        int priority;
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("initial board = null");

        MinPQ<Node> pq = init(initial);
        MinPQ<Node> pqtwin = init(initial.twin());

        moves = -1;
        solvable = true;
        solution = new Stack<Board>();

        while (true) {
            Node searchNode = pq.delMin();
            Node prevNode = searchNode.prev;

            if (searchNode.board.isGoal()) {
                moves = searchNode.moves;
                solvable = true;
                solution = findRoot(searchNode);  // find the solution path
                break;
            }

            for (Board nextBoard : searchNode.board.neighbors()) {
                if (prevNode == null || !prevNode.board.equals(nextBoard)) {
                    Node temp = new Node();
                    temp.board = nextBoard;
                    temp.prev = searchNode;
                    temp.moves = searchNode.moves + 1;
                    temp.priority = temp.moves + nextBoard.manhattan();
                    pq.insert(temp);
                }
            }

            Node searchNodeTwin = pqtwin.delMin();
            Node prevNodeTwin = searchNodeTwin.prev;

            if (searchNodeTwin.board.isGoal()) {
                solvable = false;// find the solution path
                break;
            }

            for (Board nextBoard : searchNodeTwin.board.neighbors()) {
                if (prevNodeTwin == null || !prevNodeTwin.board.equals(nextBoard)) {
                    Node temp = new Node();
                    temp.board = nextBoard;
                    temp.prev = searchNodeTwin;
                    temp.moves = searchNodeTwin.moves + 1;
                    temp.priority = temp.moves + nextBoard.manhattan();
                    pqtwin.insert(temp);
                }
            }
        }
    }

    private Stack<Board> findRoot(Node node) {
        Stack<Board> path = new Stack<>();
        path.push(node.board);
        while (node.prev != null) {
            node = node.prev;
            path.push(node.board);
        }
        return path;
    }

    private MinPQ<Node> init(Board initial) {
        Node rootNode = new Node();
        rootNode.board = initial;
        rootNode.prev = null;
        rootNode.moves = 0;
        rootNode.priority = initial.manhattan();
        MinPQ<Node> pq = new MinPQ<Node>(priority());
        pq.insert(rootNode);
        return pq;
    }

    private Comparator<Node> priority() {
        return new ByPriority();
    }


    private static class ByPriority implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            if (n1.priority > n2.priority) return +1;
            if (n1.priority < n2.priority) return -1;
            return 0;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("Unsolvable puzzle");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
