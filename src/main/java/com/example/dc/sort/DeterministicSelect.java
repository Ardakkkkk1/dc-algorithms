package com.example.dc.sort;

import java.util.Arrays;

public class DeterministicSelect {


    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Array is empty");
        if (k < 0 || k >= arr.length)
            throw new IllegalArgumentException("k out of range");

        int[] copy = Arrays.copyOf(arr, arr.length);
        return select(copy, 0, copy.length - 1, k);
    }

    private static int select(int[] a, int left, int right, int k) {
        while (true) {
            if (left == right) return a[left];

            int pivotIndex = pivotIndexMedianOfMedians(a, left, right);
            pivotIndex = partition(a, left, right, pivotIndex);

            if (k == pivotIndex) {
                return a[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }


    private static int pivotIndexMedianOfMedians(int[] a, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(a, left, right + 1);
            return (left + right) / 2;
        }

        int subRight = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            Arrays.sort(a, i, subEnd + 1);
            int medianIndex = i + (subEnd - i) / 2;
            swap(a, medianIndex, subRight++);
        }
        return pivotIndexMedianOfMedians(a, left, subRight - 1);
    }


    private static int partition(int[] a, int left, int right, int pivotIndex) {
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (a[i] < pivotValue) {
                swap(a, storeIndex++, i);
            }
        }
        swap(a, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] a, int i, int j) {
        if (i != j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
}
