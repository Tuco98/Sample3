package org.example.taskScheduler;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskScheduler scheduler = new TaskScheduler(3);

        scheduler.schedule(
                () -> System.out.println("High priority task"),
                2000,
                TaskPriority.HIGH
        );

        scheduler.schedule(
                () -> System.out.println("Low priority task"),
                2000,
                TaskPriority.LOW
        );

        scheduler.schedule(
                () -> System.out.println("Recurring task"),
                1000,
                3000,
                TaskPriority.MEDIUM
        );

        Thread.sleep(10000);
        scheduler.shutDown();
    }
}
