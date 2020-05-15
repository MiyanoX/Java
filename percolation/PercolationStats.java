/* *****************************************************************************
 *  Name:MIYANO
 *  Date:2020-05-14
 *  Description:PercolationStats
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] results;
    private double T;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new java.lang.IllegalArgumentException("n or trials must be bigger than 0");
        }
        T = trials;
        results = new double[trials];
//        System.out.println("n = " + n);
        for (int i = 0; i < trials; i++) {
//            System.out.println("i = " + i);
            Percolation per = new Percolation(n);
            int result = 0;
            while (!per.percolates()) {
//                System.out.println("result = " + result);
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                while (per.isOpen(row, col)) {
                    row = StdRandom.uniform(n) + 1;
                    col = StdRandom.uniform(n) + 1;
                }
                result++;
//                System.out.println("row = " + row + "   col = " + col);
                per.open(row, col);
//                System.out.println(per.isFull(row, col));
            }
            results[i] = result / (n * n * 1.0);
        }
        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);
        confidenceLo = mean - (1.96 * stddev / Math.sqrt(T));
        confidenceHi = mean + (1.96 * stddev / Math.sqrt(T));
    }


    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch t = new Stopwatch();
        PercolationStats unittest = new PercolationStats(500, 200);
        System.out.println("mean                    = " + unittest.mean());
        System.out.println("stddev                  = " + unittest.stddev());
        System.out.println("95% confidence interval = " + unittest.confidenceLo() + ", " + unittest.confidenceHi());
        System.out.println("running time            = " + t.elapsedTime());
    }


}
