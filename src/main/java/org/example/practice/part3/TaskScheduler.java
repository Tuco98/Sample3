package org.example.practice.part3;


import java.util.*;

public class TaskScheduler {
    PriorityQueue<Task> priorityQueue = new PriorityQueue<>((p,q)->{
        if(p.getExecuteAt()==q.getExecuteAt()){
            return q.getPriority()-p.getPriority();
        }
        return Math.toIntExact(p.executeAt - q.executeAt);
    });
    Map<String, Task> taskMap = new HashMap<>();

    String scheduleTask(int timestamp, String taskName, int executeAt){
        return scheduleTask(timestamp,taskName,executeAt,0,"NORMAL",-1);
    }

    List<String> runTasks(int currentTimestamp){
        List<String> tasks = new ArrayList<>();
        while(!priorityQueue.isEmpty() && priorityQueue.peek().getExecuteAt()<=currentTimestamp){
            Task task = priorityQueue.poll();
            if(task.getStatus().equals("CANCELLED")){
                continue;
            }
            tasks.add(task.getId());
            if(task.getType().equals("RECURRING")){
                long nextExecuteAt = currentTimestamp+task.getInterval();
                task.setExecuteAt(nextExecuteAt);
                priorityQueue.offer(task);
            }
        }

        return tasks;
    }

    boolean cancelTask(int timestamp, String taskId){
        Task task = taskMap.get(taskId);
        if(task == null) return false;
        task.setStatus("CANCELLED");
        return true;
    }

    String scheduleTask(int timestamp, String taskName, int executeAt, int priority,String type, int interval){
        Task task = new Task(taskName,executeAt,"NEW",priority,type, interval);
        taskMap.put(task.getId(),task);
        priorityQueue.offer(task);
        return task.getId();
    }

    String scheduleRecurringTask(
            int timestamp,
            String taskName,
            int firstExecutionTime,
            int interval
    ){
        return scheduleTask(timestamp,taskName,firstExecutionTime,0,"RECURRING",interval);
    }
}
