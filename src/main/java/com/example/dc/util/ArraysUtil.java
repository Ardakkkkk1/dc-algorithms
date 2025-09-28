package com.example.dc.util;

public class ArraysUtil {
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1] > a[i]) return false;
        }
        return true;
    }
}
