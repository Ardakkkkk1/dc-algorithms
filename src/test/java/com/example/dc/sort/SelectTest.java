package com.example.dc.sort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;

public class SelectTest {
    @Test
    public void testCompareWithSort() {
        Random r = new Random(7);
        for (int iter = 0; iter < 100; iter++) {
            int n = 200 + r.nextInt(800);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(10000);
            int[] copy = Arrays.copyOf(a, a.length);
            int k = r.nextInt(n);
            int expected = Arrays.stream(copy).sorted().toArray()[k];
            int got = DeterministicSelect.select(Arrays.copyOf(a, a.length), k);
            assertEquals(expected, got);
        }
    }
}
