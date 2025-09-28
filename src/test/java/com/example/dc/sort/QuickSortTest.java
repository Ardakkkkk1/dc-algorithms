package com.example.dc.sort;

import com.example.dc.util.Metrics;
import com.example.dc.util.ArraysUtil;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    public void testRandom() {
        int n = 2048;
        Random r = new Random(42);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        Metrics.reset();
        QuickSort.sort(a);
        assertTrue(ArraysUtil.isSorted(a));
        // depth check: typical bound ~ 2*log2(n) + c
        int maxDepth = Metrics.getMaxDepth();
        System.out.println("maxDepth=" + maxDepth);
        assertTrue(maxDepth < 2 * (int)(Math.log(n)/Math.log(2)) + 64);
    }
}
