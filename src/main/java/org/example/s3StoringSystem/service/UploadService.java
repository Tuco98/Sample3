package org.example.s3StoringSystem.service;

import org.example.s3StoringSystem.model.*;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.example.s3StoringSystem.service.InMemoryStore.*;

public class UploadService {
    public String initiateDownload(String bucketId, String key){

        MultipartUpload upload = new MultipartUpload();
        String id = UUID.randomUUID().toString();
        upload.setUploadId(id);
        upload.setBucket(bucketId);
        upload.setKey(key);
        upload.setCreatedAt(System.currentTimeMillis());
        upload.setStatus(UploadStatus.IN_PROGRESS);

        uploads.put(id,upload);

        return id;

    }

    public void uploadPart(String uploadId,byte[] data, int partNum, String checksum){
        MultipartUpload upload = uploads.get(uploadId);
        if(upload == null || !upload.getStatus().equals(UploadStatus.IN_PROGRESS)){
            throw new RuntimeException("Invalid upload");
        }

        Part part = new Part();
        String id = UUID.randomUUID().toString();
        part.setPartId(id);
        part.setPartNum(partNum);
        part.setUploadId(uploadId);
        part.setKey(uploads.get(uploadId).getKey());
        part.setChecksum(checksum);
        part.setSize(data.length);
        part.setStoragePath("/store:"+id);

        storage.store(part.getStoragePath(),data);

        partStore.putIfAbsent(uploadId, new ConcurrentHashMap<>())
                .put(partNum,part);

    }

    public void completeUpload(String uploadId){
        synchronized (getLock(uploadId)){
            MultipartUpload upload = uploads.get(uploadId);
            if(upload.getStatus().equals(UploadStatus.COMPLETED)) return;

            if(upload.getStatus().equals(UploadStatus.IN_PROGRESS)){
                throw new RuntimeException("ERRORRR");
            }

            Map<Integer, Part> parts = partStore.get(uploadId);

            List<Integer> partList = parts.keySet().stream().sorted().collect(Collectors.toList());

            long offset = 0, size = 0;
            for (int i=0;i<partList.size();i++){
                if(partList.get(i)!=i+1){
                    throw new RuntimeException("Missing part: "+i);
                }

                Part p = parts.get(i);
                long startOffset = offset;
                offset = (int) (offset+p.getSize()-1);
                p.setStartOffset(startOffset);
                p.setEndOffset(offset+p.getSize()-1);

                offset+=p.getSize();
                size+=p.getSize();
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setBucketId(upload.getBucket());
            objectMetadata.setKey(upload.getKey());
            objectMetadata.setSize(size);
            objectMetadata.setCreatedAt(System.currentTimeMillis());
            objectMetadata.setStatus(ObjectStatus.AVAILABLE);

            String key  = objectMetadata.getBucketId()+":"+objectMetadata.getKey();
            objects.put(key,objectMetadata);
            bucketIndex.computeIfAbsent(upload.getBucket(), k -> new TreeSet<>())
                            .add(upload.getKey());
            upload.setStatus(UploadStatus.COMPLETED);
        }
    }

    public void abortUpload(String uploadId){
        synchronized (uploadId){
            MultipartUpload upload = uploads.get(uploadId);
            if (upload != null){
                upload.setStatus(UploadStatus.ABORTED);
            }
        }

    }
}
