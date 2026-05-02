package org.example.fileDownloader;

import java.util.Arrays;

public class StorageClient {
    byte[] getRange(long start, long end){
        int size = (int) (end-start+1);
        byte[] data = new byte[size];
        Arrays.fill(data, (byte) 1);
        return data;
    }
}
