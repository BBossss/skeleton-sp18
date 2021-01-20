package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[][] grids;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF wqu;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        wqu = new WeightedQuickUnionUF(N * N + 2); // index n*n is top site, n*n+1 is bottom site;
        for (int i = 0; i < N; i++) {
            wqu.union(i, N * N);
            wqu.union(to1d(N - 1, i), N * N + 1);
        }
        grids = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grids[i][j] = false;
            }
        }
        numberOfOpenSites = 0;
    }

    private boolean isIndexOutOfBound(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return true;
        }
        return false;
    }

    private int to1d(int row, int col) {
        return row * N + col;
    }

    private void unionAround(int row, int col) {
        if (row > 0 && isOpen(row - 1, col)) {
            wqu.union(to1d(row, col), to1d(row - 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            wqu.union(to1d(row, col), to1d(row, col - 1));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            wqu.union(to1d(row, col), to1d(row + 1, col));
        }

        if (col < N - 1 && isOpen(row, col + 1)) {
            wqu.union(to1d(row, col), to1d(row, col + 1));
        }
    }

    public void open(int row, int col) {
        if (isIndexOutOfBound(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return ;
        }
        grids[row][col] = true;
        unionAround(row, col);
        numberOfOpenSites += 1;
    }

    public boolean isOpen(int row, int col) {
        if (isIndexOutOfBound(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grids[row][col];
    }

    public boolean isFull(int row, int col) {
        if (isIndexOutOfBound(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        /* for (int i = 0; i < N; i++) {
            if (wqu.connected(To1d(row, col), i)) {
                return true;
            }
        }
        return false;*/
        return wqu.connected(to1d(row, col), N * N);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (wqu.connected(i, To1d(N - 1, j))) {
                    return true;
                }
            }
        }
        return false;*/
        if (N == 1 && !isOpen(0, 0)) {
            return false;
        }

        return wqu.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) {

    }
}
