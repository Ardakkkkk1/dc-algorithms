package com.example.dc.util;

import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    public static AtomicLong comparisons = new AtomicLong(0);
    public static final AtomicLong swaps = new AtomicLong(0);
    public static final AtomicLong allocations = new AtomicLong(0);
    private static final ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);
    private static final ThreadLocal<Integer> maxDepth = ThreadLocal.withInitial(() -> 0);

    public static void reset() {
        comparisons.set(0);
        swaps.set(0);
        allocations.set(0);
        depth.set(0);
        maxDepth.set(0);
    }

    public static void enterRecursion() {
        int d = depth.get() + 1;
        depth.set(d);
        if (d > maxDepth.get()) maxDepth.set(d);
    }

    public static void exitRecursion() {
        depth.set(depth.get() - 1);
    }

    public static int getMaxDepth() {
        return maxDepth.get();
    }

    public static void incComparisons(long v) {
        comparisons.addAndGet(v);
    }

    public static void incSwaps(long v) {
        swaps.addAndGet(v);
    }

    public static void incAllocations(long v) {
        allocations.addAndGet(v);
    }
}
