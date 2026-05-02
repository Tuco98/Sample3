package org.example.fileDownloader;

import java.util.Arrays;

public class CheckSumUtil {
    public static String hash(byte[] data){
        return String.valueOf(Arrays.hashCode(data));
    }
}
