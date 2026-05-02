package org.example.fileDownloader;

public class Chunk {
    int id;
    long start;
    long end;
    boolean status;

    public Chunk(int id, long start, long end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}
