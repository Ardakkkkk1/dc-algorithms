package com.example.dc.sort;

import com.example.dc.util.DepthTracker;
import com.example.dc.util.Metrics;

public class MergeSort {
    private static final int CUT_OFF = 16;

    public static void sort(int[] a) {
        Metrics.incAllocations(1); // buffer
        int[] buf = new int[a.length];
        sort(a, buf, 0, a.length);
    }

    private static void sort(int[] a, int[] buf, int l, int r) {
        if (r - l <= CUT_OFF) {
            insertionSort(a, l, r);
            return;
        }
        try (DepthTracker dt = new DepthTracker()) {
            int m = (l + r) >>> 1;
            sort(a, buf, l, m);
            sort(a, buf, m, r);
            merge(a, buf, l, m, r);
        }
    }

    private static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i < r; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= l) {
                Metrics.incComparisons(1);
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    Metrics.incSwaps(1);
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
            Metrics.incAllocations(1);
        }
    }


    private static void merge(int[] a, int[] buf, int l, int m, int r) {
        int i = l, j = m, k = l;
        while (i < m && j < r) {
            Metrics.incComparisons(1);
            if (a[i] <= a[j]) buf[k++] = a[i++]; else buf[k++] = a[j++];
            Metrics.incAllocations(1);
        }
        while (i < m) { buf[k++] = a[i++]; Metrics.incAllocations(1); }
        while (j < r) { buf[k++] = a[j++]; Metrics.incAllocations(1); }
        System.arraycopy(buf, l, a, l, r - l);
        Metrics.incAllocations(r - l);
    }
}
