package com.example.dc.util;

public class DepthTracker implements AutoCloseable {
    public DepthTracker() {
        Metrics.enterRecursion();
    }
    @Override
    public void close() {
        Metrics.exitRecursion();
    }
}
