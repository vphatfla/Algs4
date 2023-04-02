package week2;

import edu.princeton.cs.algs4.StdRandom;

import static edu.princeton.cs.algs4.QuickBentleyMcIlroy.exch;
import static edu.princeton.cs.algs4.QuickBentleyMcIlroy.less;

public class QuickSortEx {
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    private static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }

        return a[k];
    }

    public static void main(String[] args) {
        Comparable[] a = {2, 3, 5, 6, 1, 4};
//2,3,4,5,6,8
        //System.out.println(partition(a, 0, a.length - 1));
        System.out.println(select(a, 2));

        for (int i = 0; i < a.length; i += 1) {
            System.out.print(a[i] + "  ");
        }
    }
}
