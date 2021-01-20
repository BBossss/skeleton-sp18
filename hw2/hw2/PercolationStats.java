package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] xs;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        xs = new int[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int t = 0;
            while (!p.percolates()) {
                t++;
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                }
                p.open(row, col);
            }
            xs[i] = t / N * N * 1.0;
        }
    }

    public double mean() {
        return StdStats.mean(xs);
    }

    public double stddev() {
        return StdStats.stddev(xs);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
