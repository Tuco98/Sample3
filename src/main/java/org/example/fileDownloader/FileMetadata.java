package org.example.fileDownloader;

import java.util.Map;

public class FileMetadata {
    String fileId;
    long size;
    int chunkSize;
    String checkSum;
    Map<Integer,String> chunkHashes;

    public FileMetadata(String fileId, long size, int chunkSize, String checkSum, Map<Integer, String> chunkHashes) {
        this.fileId = fileId;
        this.size = size;
        this.chunkSize = chunkSize;
        this.checkSum = checkSum;
        this.chunkHashes = chunkHashes;
    }
}
