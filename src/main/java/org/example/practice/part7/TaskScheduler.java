package org.example.practice.part7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {
    PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.executeAt));
    Map<String , Task> taskMap = new HashMap<>();
    Thread worker;
    TaskExecutor executor;
    RetryHandler handler;

    Object lock = new Object();

    void addTask(Task t){
        synchronized (lock){
            taskMap.put(t.id,t);
            pq.offer(t);
            lock.notify();
        }
    }

    void cancelTask(String id){
        Task task = taskMap.get(id);
        if (task!= null){
            task.status = TaskStatus.CANCELLED;
        }
    }

    void start(){
        worker = new Thread(()->{
            while (true){
                Task t = null;
                synchronized (lock){
                    while (pq.isEmpty()){
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {

                        }
                    }

                    t = pq.peek();
                    long now = System.currentTimeMillis();

                    if(t.executeAt > now){
                        try {
                            lock.wait(t.executeAt-now);
                        } catch (InterruptedException e) {
                            continue;
                        }
                    }
                    pq.poll();
                }

                if(t.status.equals(TaskStatus.CANCELLED)){
                    continue;
                }

                try{
                    executor.execute(t.job);

                    t.status = TaskStatus.COMPLETED;
                } catch (Exception e){
                    t.status = TaskStatus.FAILED;
                    handler.retry(t);
                    continue;
                }

                if(t.type.equals(TaskType.RECURRING)){
                    t.executeAt = System.currentTimeMillis()+ t.interval;
                    t.status = TaskStatus.SCHEDULED;
                    addTask(t);
                }

            }

        });

        worker.start();
    }
}
