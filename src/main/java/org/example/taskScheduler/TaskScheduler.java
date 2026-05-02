package org.example.taskScheduler;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.taskScheduler.TaskPriority.MEDIUM;

public class TaskScheduler {
    private DelayQueue<ScheduledTask> queue = new DelayQueue<>();
    private ExecutorService workerPool;
    private volatile boolean running = true;

    public TaskScheduler(int numOfThreads) {
        this.workerPool = Executors.newFixedThreadPool(numOfThreads);
        startDispatcher();
    }

    private void startDispatcher() {
        Thread dispatcher = new Thread(()->{
            while (running){
                try {

                    ScheduledTask finalTask = queue.take();;
                    workerPool.submit(()->{
                        finalTask.getTask().run();

                        //if recurring task
                        schedule(finalTask.getTask(), finalTask.getPeriod(),finalTask.getPeriod(),MEDIUM);
                    });



                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        });

        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public void schedule(Runnable task, long period, long period1, TaskPriority taskPriority) {
        queue.offer(new ScheduledTask(task,taskPriority,period,period1));
    }

    public void schedule(Runnable task, long period, TaskPriority taskPriority) {
        queue.offer(new ScheduledTask(task,taskPriority,period,0));
    }

    public void shutDown(){
        running = false;
        workerPool.shutdown();
    }
}
