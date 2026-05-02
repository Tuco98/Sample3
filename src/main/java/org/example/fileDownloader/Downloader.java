package org.example.fileDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Downloader {
    DownloadState downloadState;
    FileMetadata fileMetadata;
    StorageClient sc;
    RetryPolicy rp;
    ExecutorService ex;

    public Downloader(DownloadState downloadState, FileMetadata fileMetadata) {
        this.downloadState = downloadState;
        this.fileMetadata = fileMetadata;
        this.sc = new StorageClient();
        this.rp = new RetryPolicy(3,1000);
        this.ex = Executors.newFixedThreadPool(5);
    }

    List<Chunk> createChunks(){
        List<Chunk> chunks = new ArrayList<>();
        int id = 0;

        for(long i=0;i< fileMetadata.size;i+= fileMetadata.chunkSize){
            chunks.add(new Chunk(id++,i,Math.min(i+ fileMetadata.chunkSize-1, fileMetadata.size)-1));
        }
        return chunks;
    }

    void start() throws ExecutionException, InterruptedException, IOException {
        List<Chunk> chunks = createChunks();
        List<Future<Boolean>> res = new ArrayList<>();

        for (Chunk c: chunks){
            if(downloadState.downloadedChunks.contains(c.id)) continue;
            res.add(ex.submit(new DownloaderTask(c,downloadState,sc,rp,fileMetadata)));
        }

        for (Future<Boolean> f:res) {
            if (!f.get()) {
                throw new RuntimeException("Some exception");
            }
        }
        ex.shutdown();
        verify();

    }

    void verify() throws IOException {
        File f = new File(downloadState.path);
        byte[] data = new byte[(int)f.length()];
        FileInputStream fis = new FileInputStream(f);
        fis.read(data);
        fis.close();

        String h = CheckSumUtil.hash(data);
        if(!h.equals(fileMetadata.checkSum)){
            throw new RuntimeException("Corrupted");
        }

        System.out.println("Download verified");
    }
}
