package org.example.taskScheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ScheduledTask implements Delayed {
    private Runnable task;
    private TaskPriority priority;
    private long executeAt;
    private long period;

    public ScheduledTask(Runnable task, TaskPriority priority, long delayMilli, long period) {
        this.task = task;
        this.priority = priority;
        this.executeAt = System.currentTimeMillis()+delayMilli;
        this.period = period;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public long getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(long executeAt) {
        this.executeAt = executeAt;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = executeAt - System.currentTimeMillis();
        return unit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        ScheduledTask scheduledTask = (ScheduledTask) o;

        if(this.executeAt < ((ScheduledTask) o).executeAt) return -1;
        if(this.executeAt > ((ScheduledTask) o).executeAt) return 1;

        return Integer.compare(this.priority.getPriority(),((ScheduledTask) o).priority.getPriority());
    }
}
