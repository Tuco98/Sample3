package org.example.practice.part10;

public class Consumer implements Runnable{
    String id;
    Partition partition;
    int offset;

    public Consumer(String id, Partition partition) {
        this.id = id;
        this.partition = partition;
        this.offset = 0;
    }

    @Override
    public void run() {

        try {
            while (!Thread.currentThread().isInterrupted()){
                Message message = partition.read(offset);
                System.out.println(message.toString());
                offset++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
