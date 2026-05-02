package org.example.s3StoringSystem.service;

import org.example.s3StoringSystem.model.Bucket;
import org.example.s3StoringSystem.model.ObjectMetadata;

import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.stream.Collectors;

import static org.example.s3StoringSystem.service.InMemoryStore.*;

public class BucketService {
    public  void createBucket(String bucketId, String ownerId){
        Bucket bucket = bucketMap.get(bucketId);
        if(bucket!=null){
            throw new RuntimeException("Already bucket exists");
        }

        Bucket newBucket = new Bucket();
        bucket.setCreatedAt(System.currentTimeMillis());
        bucket.setId(bucketId);
        bucket.setOwner(ownerId);

    }

    Bucket getBucket(String bucketId) {
        return bucketMap.get(bucketId);
    }

    void deleteBucket(String bucketId) {
        bucketMap.remove(bucketId);
    }

    public List<ObjectMetadata> prefixSearch(String bucketId,String prefix){
        NavigableSet<String> keys = bucketIndex.get(bucketId);
        if(keys == null) return Collections.emptyList();

        String end = prefix+Character.MAX_VALUE;

        NavigableSet<String> list = keys.subSet(prefix, true, end, true);

        return list.stream().map(e -> objects.get(bucketId + ":" + e)).collect(Collectors.toList());
    }
}
