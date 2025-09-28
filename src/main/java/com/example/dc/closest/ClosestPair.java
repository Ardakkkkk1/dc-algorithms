package com.example.dc.closest;

import com.example.dc.util.DepthTracker;
import com.example.dc.util.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closest(Point[] pts) {
        Point[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p -> p.x));
        Point[] buf = new Point[pts.length];
        return closestRec(byX, buf, 0, pts.length);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }

    private static double bruteForce(Point[] a, int l, int r) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = l; i < r; i++) for (int j = i+1; j < r; j++) {
            double d = dist(a[i], a[j]);
            if (d < best) best = d;
            Metrics.incComparisons(1);
        }
        return best;
    }

    private static double closestRec(Point[] a, Point[] buf, int l, int r) {
        int n = r - l;
        if (n <= 3) return bruteForce(a, l, r);
        try (DepthTracker dt = new DepthTracker()) {
            int m = (l + r) >>> 1;
            double midx = a[m].x;
            double dl = closestRec(a, buf, l, m);
            double dr = closestRec(a, buf, m, r);
            double d = Math.min(dl, dr);
            // build strip
            int k = 0;
            for (int i = l; i < r; i++) {
                if (Math.abs(a[i].x - midx) < d) {
                    buf[k++] = a[i];
                    Metrics.incAllocations(1);
                }
            }
            Arrays.sort(buf, 0, k, Comparator.comparingDouble(p -> p.y));
            for (int i = 0; i < k; i++) {
                for (int j = i+1; j < k && (buf[j].y - buf[i].y) < d; j++) {
                    d = Math.min(d, dist(buf[i], buf[j]));
                    Metrics.incComparisons(1);
                }
            }
            return d;
        }
    }
}
