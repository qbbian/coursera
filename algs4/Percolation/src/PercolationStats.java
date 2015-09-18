/**
 * Created by bbian-chrome on 9/18/15.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    public PercolationStats(int N, int T) {
        if ((N <=0) || (T <=0)) {
            throw new IllegalArgumentException();
        }

        Percolation perc = new Percolation(N);

        for (int i = 0; i < T; i++) {
            int row = StdRandom.uniform(N) + 1;
            int column = StdRandom.uniform(N) + 1;
            perc.open(row, column);
        }
    }

    public double mean() {

    }

    public double stddev() {

    }

    public double confidenceLo() {

    }

    public double confidenceHi() {

    }

    public static void main(String[] args) {
        int N = args[0
    }
}
