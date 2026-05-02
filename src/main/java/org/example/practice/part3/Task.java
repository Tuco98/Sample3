package org.example.practice.part3;

import java.util.UUID;

public class Task {
    String id;
    String taskName;
    long executeAt;
    String status;
    int priority;
    String type;
    int interval;

    public Task(String taskName, long executeAt, String status, int priority, String type, int interval) {
        this.id = UUID.randomUUID().toString();
        this.taskName = taskName;
        this.executeAt = executeAt;
        this.status = status;
        this.priority = priority;
        this.type = type;
        this.interval = interval;
    }

    public String getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(long executeAt) {
        this.executeAt = executeAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
