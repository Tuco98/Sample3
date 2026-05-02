package org.example.s3StoringSystem.service;

import org.example.s3StoringSystem.model.Bucket;
import org.example.s3StoringSystem.model.MultipartUpload;
import org.example.s3StoringSystem.model.ObjectMetadata;
import org.example.s3StoringSystem.model.Part;

import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStore {
    public static Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();
    public static Map<String, MultipartUpload> uploads = new ConcurrentHashMap<>();
    public static Map<String,Map<Integer, Part>> partStore = new ConcurrentHashMap<>();
    public static Map<String, ObjectMetadata> objects = new ConcurrentHashMap<>();
    public static Map<String, Object> locks = new ConcurrentHashMap<>();

    public static Object getLock(String uploadId){
        locks.putIfAbsent(uploadId, new Object());
        return locks.get(uploadId);
    }

    public static StorageLayer storage = new StorageLayer();

    public static Map<String, NavigableSet<String>> bucketIndex = new ConcurrentHashMap<>();


}
