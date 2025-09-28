package com.example.dc.util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final String path;
    public CSVWriter(String path) { this.path = path; }

    public void write(String header, String[] rows) throws IOException {
        try (FileWriter w = new FileWriter(path)) {
            w.write(header + "\n");
            for (String r : rows) {
                w.write(r + "\n");
            }
        }
    }
}
