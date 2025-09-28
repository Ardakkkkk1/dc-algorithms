package com.example.dc.sort;

import com.example.dc.util.DepthTracker;
import com.example.dc.util.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] a) {
        shuffle(a);
        sortIterative(a, 0, a.length - 1);
    }

    private static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            Metrics.incSwaps(1);
        }
    }

    private static void sortIterative(int[] a, int lo, int hi) {
        while (lo < hi) {
            int p = partition(a, lo, hi);
            int leftSize = p - lo;
            int rightSize = hi - p;
            // recurse smaller side, iterate larger side
            if (leftSize < rightSize) {
                try (DepthTracker dt = new DepthTracker()) {
                    sortIterative(a, lo, p - 1);
                }
                lo = p + 1;
            } else {
                try (DepthTracker dt = new DepthTracker()) {
                    sortIterative(a, p + 1, hi);
                }
                hi = p - 1;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, hi);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            Metrics.incComparisons(1);
            if (a[j] < pivot) {
                swap(a, i++, j);
            }
        }
        swap(a, i, hi);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        Metrics.incSwaps(1);
    }
}
