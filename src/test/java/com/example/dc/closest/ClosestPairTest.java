package com.example.dc.closest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class ClosestPairTest {
    @Test
    public void testAgainstBrute() {
        Random rnd = new Random(1);
        int n = 500;
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) pts[i] = new ClosestPair.Point(rnd.nextDouble()*100, rnd.nextDouble()*100);
        double fast = ClosestPair.closest(pts);
        // brute force
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) for (int j = i+1; j < n; j++) {
            double dx = pts[i].x - pts[j].x, dy = pts[i].y - pts[j].y;
            best = Math.min(best, Math.hypot(dx, dy));
        }
        assertEquals(best, fast, 1e-9);
    }
}
