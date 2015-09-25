/**
 * Created by bbian-chrome on 9/25/15.
 */
import edu.princeton.cs.algs4.*;

public class Subset {
    private static void exchange(String[] a, int i, int r) {
        String tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
    }

    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);

        String[] a = StdIn.readAllStrings();
        for (int i = 0; i < a.length; i++) {
            int r = StdRandom.uniform(i+1);
            exchange(a, i, r);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(a[i]);
        }
    }
}
