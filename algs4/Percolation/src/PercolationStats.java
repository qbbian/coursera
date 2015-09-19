/**
 * Created by bbian-chrome on 9/18/15.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static int N;
    private static int T;
    private double fraction[];

    public PercolationStats(int N, int T) {
        if ((N <= 0) || (T <= 0)) {
            throw new IllegalArgumentException();
        }

        fraction = new double[T];
        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int column = StdRandom.uniform(N) + 1;
                perc.open(row, column);
                count++;
            }
            fraction[i] = (double) count / (double) (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(fraction);
    }

    public double stddev() {
        return StdStats.stddev(fraction);
    }

    public double confidenceLo() {
        double meanVal = mean();
        double stddevVal = stddev();

        return meanVal - 1.96 * stddevVal / Math.sqrt(T);
    }

    public double confidenceHi() {
        double meanVal = mean();
        double stddevVal = stddev();

        return meanVal + 1.96 * stddevVal / Math.sqrt(T);
    }

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);

        PercolationStats pstat = new PercolationStats(N, T);

        System.out.println("mean" + "\t\t\t" + "= " + pstat.mean());
        System.out.println("stddev" + "\t\t\t" + "= " + pstat.stddev());
        System.out.println("95% confidence interval" + "\t" + "= " + pstat.confidenceLo() + ", " + pstat.confidenceHi());
    }
}
