package org.example.practice.part7;

public class RetryHandler {
    TaskScheduler scheduler;

    public  void retry(Task t){
        t.executeAt = t.executeAt+5000;
        t.status = TaskStatus.SCHEDULED;

        scheduler.addTask(t);
    }
}
