package org.example.practice.part7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {
    ExecutorService workerPool = Executors.newFixedThreadPool(8);

    void execute(Runnable job){
        workerPool.submit(job);
    }
}
