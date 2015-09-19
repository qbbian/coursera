/**
 * Created by bbian-chrome on 9/18/15.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] site;
    private int N;
    WeightedQuickUnionUF uf;

    private int mapToId(int i, int j) {
        return ((i-1) * N + j);
    }
    public Percolation(int N) {
        if (N <=0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        site = new int[N][N];
        for (int i = 1; i < N; i++)
            for (int j = 1; j < N; j++)
                site[i][j] = 0;

        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {
        if ((i < 1) || (j < 1) || (i > N) || (j > N)) {
            throw new IndexOutOfBoundsException();
        }

        site[i-1][j-1] = 1;
        int p = mapToId(i, j);
        int q = 0;

        if (i == 1) {
            uf.union(p, 0);
        }

        if (i == N) {
            uf.union(p, N * N + 1);
        }

        if ((j > 1) && isOpen(i, j - 1)) {
            q = mapToId(i, j - 1);
            uf.union(p, q);
        }

        if ((j < N) && isOpen(i, j + 1)) {
            q = mapToId(i, j + 1);
            uf.union(p, q);
        }

        if ((i > 1) && isOpen(i - 1, j)) {
            q = mapToId(i - 1, j);
            uf.union(p, q);
        }

        if ((i < N) && isOpen(i + 1, j)) {
            q = mapToId(i + 1, j);
            uf.union(p, q);
        }
    }

    public boolean isOpen(int i, int j) {
        if ((i < 1) || (j < 1) || (i > N) || (j > N)) {
            throw new IndexOutOfBoundsException();
        }

        return site[i-1][j-1] == 1;
    }

    public boolean isFull(int i, int j) {
        if ((i < 1) || (j < 1) || (i > N) || (j > N)) {
            throw new IndexOutOfBoundsException();
        }

        int p = mapToId(i, j);
        return uf.connected(p, 0);
    }

    public boolean percolates() {
        return uf.connected(N * N + 1, 0);
    }

    public static void main(String args[]) {
        Percolation foo = new Percolation(5);

        System.out.println("isOpen [3, 4]: " + foo.isOpen(3, 4));
        System.out.println("isFull [1, 1]: " + foo.isFull(1, 1));
    }
}
