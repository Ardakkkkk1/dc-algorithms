package com.example.dc;

import com.example.dc.util.Metrics;
import com.example.dc.sort.MergeSort;
import com.example.dc.sort.QuickSort;
import com.example.dc.sort.DeterministicSelect;
import com.example.dc.closest.ClosestPair;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // Simple CLI: java -jar ... mergesort 100000
        if (args.length < 2) {
            System.out.println("Usage: <algorithm> <n>");
            System.out.println("algorithms: mergesort quicksort select closest");
            return;
        }
        String algo = args[0];
        int n = Integer.parseInt(args[1]);
        int[] a = generateRandom(n);
        Metrics.reset();
        long t0 = System.nanoTime();
        switch (algo) {
            case "mergesort":
                MergeSort.sort(a);
                break;
            case "quicksort":
                QuickSort.sort(a);
                break;
            case "select":
                int k = n/2;
                DeterministicSelect.select(Arrays.copyOf(a, a.length), k);
                break;
            case "closest":
                // build random points
                com.example.dc.closest.ClosestPair.Point[] pts = new com.example.dc.closest.ClosestPair.Point[n];
                Random rnd = new Random();
                for (int i = 0; i < n; i++) pts[i] = new com.example.dc.closest.ClosestPair.Point(rnd.nextDouble(), rnd.nextDouble());
                ClosestPair.closest(pts);
                break;
            default:
                System.out.println("Unknown algorithm.");
                return;
        }
        long t1 = System.nanoTime();
        System.out.printf("Time ms: %.3f, comparisons=%d, swaps=%d, allocs=%d, maxDepth=%d\n",
                (t1 - t0)/1e6,
                Metrics.comparisons.get(),
                Metrics.swaps.get(),
                Metrics.allocations.get(),
                Metrics.getMaxDepth());
    }

    private static int[] generateRandom(int n) {
        Random r = new Random();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }
}
