package com.example.dc.sort;

import com.example.dc.util.Metrics;
import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] a, int k) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("k out of bounds");
        return select(a, 0, a.length - 1, k);
    }

    private static int select(int[] a, int l, int r, int k) {
        while (true) {
            if (l == r) return a[l];
            int pivot = medianOfMedians(a, l, r);
            int p = partition(a, l, r, pivot);
            if (k == p) return a[k];
            else if (k < p) r = p - 1;
            else l = p + 1;
        }
    }

    private static int partition(int[] a, int l, int r, int pivotValue) {
        int store = l;
        for (int i = l; i <= r; i++) {
            Metrics.incComparisons(1);
            if (a[i] < pivotValue) {
                swap(a, store++, i);
            }
        }
        // place pivot value at store
        int pivotIndex = -1;
        for (int i = l; i <= r; i++) if (a[i] == pivotValue) { pivotIndex = i; break; }
        if (pivotIndex == -1) pivotIndex = r;
        swap(a, store, pivotIndex);
        return store;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        Metrics.incSwaps(1);
    }

    private static int medianOfMedians(int[] a, int l, int r) {
        int n = r - l + 1;
        if (n <= 5) {
            int[] tmp = Arrays.copyOfRange(a, l, r + 1);
            Arrays.sort(tmp);
            return tmp[n/2];
        }
        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subL = l + i*5;
            int subR = Math.min(subL + 4, r);
            int[] tmp = Arrays.copyOfRange(a, subL, subR + 1);
            Arrays.sort(tmp);
            int median = tmp[(tmp.length-1)/2];
            a[l + i] = median;
            Metrics.incAllocations(1);
        }
        return medianOfMedians(a, l, l + numMedians - 1);
    }
}
