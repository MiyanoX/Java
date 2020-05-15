import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int n;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF UF1; // isFull judgement
    private int numOpen = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {            //n must bigger than 0
        if (n < 1) {
            throw new java.lang.IllegalArgumentException("N must be bigger than 0");
        }
        this.n = n;
        grid = new boolean[n + 1][n + 1];
        WeightedQuickUnionUF UF = new WeightedQuickUnionUF(n * n + 2);
        WeightedQuickUnionUF UF1 = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException("row & col must between 1 ~ n");
        if (!grid[row][col]) {
            grid[row][col] = true;
            if (row == 1) {
                UF.connected(0, col);
                UF1.connected(0, col);
            }
            if (row == n) {
                UF.connected(n * n + 1, (row - 1) * n + col);
            }
            // up
            if (row > 1) {
                if (isOpen(row - 1, col)) {
                    UF.connected((row - 1) * n + col, (row - 2) * n + col);
                    UF.connected((row - 1) * n + col, (row - 2) * n + col);
                }
            }
            // down
            if (row < n) {
                if (isOpen(row + 1, col)) {
                    UF.connected((row - 1) * n + col, (row) * n + col);
                    UF.connected((row - 1) * n + col, (row) * n + col);
                }
            }
            // left
            if (col > 1) {
                if (isOpen(row, col - 1)) {
                    UF.connected((row - 1) * n + col, (row - 1) * n + col - 1);
                    UF.connected((row - 1) * n + col, (row - 1) * n + col - 1);
                }
            }
            // right
            if (col < n) {
                if (isOpen(row, col + 1)) {
                    UF.connected((row - 1) * n + col, (row - 1) * n + col + 1);
                    UF.connected((row - 1) * n + col, (row - 1) * n + col + 1);
                }
            }
            numOpen++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException("row & col must between 1 ~ n");
        if (grid[row][col]) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException("row & col must between 1 ~ n");
        if (grid[row][col]) {
            if (UF1.connected(0, (row - 1) * n + col)) return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.connected(0, n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("Good");
    }
}

