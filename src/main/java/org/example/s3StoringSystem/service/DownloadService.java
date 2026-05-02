package org.example.s3StoringSystem.service;

import org.example.s3StoringSystem.model.ObjectMetadata;
import org.example.s3StoringSystem.model.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.s3StoringSystem.service.InMemoryStore.*;

public class DownloadService {
    public byte[] downloadRange(String key, String bucket,long start, long end) throws IOException {
        ObjectMetadata objectMetadata = objects.get(bucket + ":" + key);
        if (objectMetadata == null){
            throw new RuntimeException("No such object");
        }

        List<Part> parts = partStore.get(objectMetadata.getKey()).values().stream().sorted(Comparator.comparingInt(Part::getPartNum)).collect(Collectors.toList());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for(Part p:parts){
            if(p.getEndOffset() < start) continue;
            if(p.getStartOffset() > end) break;

            long localStart = Math.max(start, p.getStartOffset())- p.getStartOffset();
            long localEnd = Math.min(end, p.getEndOffset()) - p.getEndOffset();

            byte[] data = storage.read(p.getStoragePath(), localStart, localEnd);
            outputStream.write(data);
        }

        return outputStream.toByteArray();


    }
}
