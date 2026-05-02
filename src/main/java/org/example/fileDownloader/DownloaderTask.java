package org.example.fileDownloader;

import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class DownloaderTask implements Callable<Boolean> {
    Chunk c;
    DownloadState downloadState;
    StorageClient st;
    RetryPolicy rt;
    FileMetadata fileMetadata;

    public DownloaderTask(Chunk c, DownloadState downloadState, StorageClient st, RetryPolicy rt, FileMetadata fileMetadata) {
        this.c = c;
        this.downloadState = downloadState;
        this.st = st;
        this.rt = rt;
        this.fileMetadata = fileMetadata;
    }


    @Override
    public Boolean call() throws Exception {
        int tries = 0;
        while (tries<rt.maxRetries){

            try{
                byte[] range = st.getRange(c.start, c.end);
                String h = CheckSumUtil.hash(range);
                String expected = fileMetadata.chunkHashes.get(c.id);

                if(!h.equals(expected)){
                    throw new RuntimeException("Not matching");
                }

                RandomAccessFile r = new RandomAccessFile(downloadState.path,"rw");
                r.seek(c.start);
                r.write(range);
                r.close();
                downloadState.downloadedChunks.add(c.id);
                c.status = true;
                return true;
            } catch (Exception e){
                tries++;
                try{
                    Thread.sleep(rt.backOff);
                } catch (Exception ignored){

                }
            }

        }
        return false;
    }
}
