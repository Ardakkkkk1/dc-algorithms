package com.example.dc.sort;

import com.example.dc.util.Metrics;
import com.example.dc.util.ArraysUtil;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    public void testRandom() {
        int n = 1000;
        Random r = new Random(123);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(10000);
        Metrics.reset();
        MergeSort.sort(a);
        assertTrue(ArraysUtil.isSorted(a));
    }
}
