package org.example.taskScheduler;

public enum TaskPriority {
    HIGH(1),MEDIUM(2),LOW(3);

    private final int priority;

    TaskPriority(int i) {
        this.priority = i;
    }

    public int getPriority() {
        return priority;
    }
}
