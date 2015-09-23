/**
 * Created by bbian-chrome on 9/18/15.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private double[] fraction;

    public PercolationStats(int n, int t) {
        if ((n <= 0) || (t <= 0)) {
            throw new IllegalArgumentException();
        }

        this.t = t;

        fraction = new double[t];
        for (int i = 0; i < t; i++) {
            int count = 0;
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int column = StdRandom.uniform(n) + 1;
                if (!perc.isOpen(row, column)) {
                    perc.open(row, column);
                    count++;
                }
            }
            fraction[i] = (double) count / (double) (n * n);
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

        return meanVal - 1.96 * stddevVal / Math.sqrt(t);
    }

    public double confidenceHi() {
        double meanVal = mean();
        double stddevVal = stddev();

        return meanVal + 1.96 * stddevVal / Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationStats pstat = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("95% confidence interval" + "\t" + "= " + pstat.confidenceLo() + ", " + pstat.confidenceHi());
        System.out.println("mean" + "\t\t\t" + "= " + pstat.mean());
        System.out.println("stddev" + "\t\t\t" + "= " + pstat.stddev());
     }
}
