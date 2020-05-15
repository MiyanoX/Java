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
        UF = new WeightedQuickUnionUF(n * n + 2);
        UF1 = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException("row & col must between 1 ~ n");
        if (!grid[row][col]) {
            grid[row][col] = true;
            if (row == 1) {
//                System.out.println(row + "   " + col + "    " + "top");
                UF.union(0, col);
                UF1.union(0, col);
            }
            if (row == n) {
//                System.out.println(row + "   " + col + "    " + "bottom");
                UF.union(n * n + 1, (row - 1) * n + col);
            }
            // up
            if (row > 1) {
                if (isOpen(row - 1, col)) {
//                    System.out.println((row - 1) + "   " + col + "    " + "up");
                    UF.union((row - 1) * n + col, (row - 2) * n + col);
                    UF1.union((row - 1) * n + col, (row - 2) * n + col);
                }
            }
            // down
            if (row < n) {
                if (isOpen(row + 1, col)) {
//                    System.out.println((row + 1) + "   " + col + "    " + "down");
                    UF.union((row - 1) * n + col, (row) * n + col);
                    UF1.union((row - 1) * n + col, (row) * n + col);
                }
            }
            // left
            if (col > 1) {
                if (isOpen(row, col - 1)) {
//                    System.out.println(row + "   " + (col - 1) + "    " + "left");
                    UF.union((row - 1) * n + col, (row - 1) * n + col - 1);
                    UF1.union((row - 1) * n + col, (row - 1) * n + col - 1);
                }
            }
            // right
            if (col < n) {
                if (isOpen(row, col + 1)) {
//                    System.out.println(row + "   " + (col + 1) + "    " + "right");
                    UF.union((row - 1) * n + col, (row - 1) * n + col + 1);
                    UF1.union((row - 1) * n + col, (row - 1) * n + col + 1);
                }
            }
            numOpen++;
//            System.out.println("connected: " + UF.connected(0, n * n + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException("row & col must between 1 ~ n");
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException("row & col must between 1 ~ n");
        if (grid[row][col]) {
            return UF1.connected(0, (row - 1) * n + col);
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
    }
}

