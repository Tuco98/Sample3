package org.example.fileDownloader;

import java.util.HashSet;
import java.util.Set;

public class DownloadState {
    String fileId;
    Set<Integer> downloadedChunks;
    String path;

    public DownloadState(String fileId, String path) {
        this.fileId = fileId;
        this.downloadedChunks = new HashSet<>();
        this.path = path;
    }
}
