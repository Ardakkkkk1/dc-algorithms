package com.example.dc.util;

import java.util.Random;

public class Shuffle {
    private static final Random rnd = new Random();

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            Metrics.incSwaps(1);
        }
    }
}
