import java.util.Stack;

public class Board {
    private int n;  // dimension of array
    private int[][] tiles;
    private int ha;
    private int ma;
    private int x, y;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tile) {
        if (tile == null) throw new IllegalArgumentException("tiles = null");
        n = tile.length;
        tiles = new int[n][n];
        ha = 0;
        ma = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = tile[i][j];

                // record the coordinate of zero point
                if (tiles[i][j] == 0) {
                    x = i;
                    y = j;
                    continue;
                }

                int index = i * n + j + 1;
                if (tiles[i][j] != i * n + j + 1) {
                    ha++;
                }
                ma += Math.abs(i - (index - 1) / n) + Math.abs(j - (index - 1) % n);

            }
        }

    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return ha;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return ma;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int index;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                index = i * n + j + 1;
                if (index < n * n && tiles[i][j] != index) {
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y; // change y into board type
        if (that.n != this.n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (that.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighborsBoards = new Stack<Board>();
        if (x > 0) neighborsBoards.push(move(x - 1, y));  //need to create a board with neighbours block move
        if (y > 0) neighborsBoards.push(move(x, y - 1));
        if (x < n - 1) neighborsBoards.push(move(x + 1, y));
        if (y < n - 1) neighborsBoards.push(move(x, y + 1));
        return neighborsBoards;
    }

    private Board move(int x1, int y1) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        swap(copy, x, y, x1, y1);
        return new Board(copy);
    }

    private void swap(int[][] arr, int x1, int y1, int x2, int y2) {
        int temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }

        if (copy[0][0] == 0) swap(copy, 0, 1, 1, 0);
        else if (copy[0][1] == 0) swap(copy, 0, 0, 1, 0);
        else swap(copy, 0, 0, 0, 1);

        return new Board(copy);
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
