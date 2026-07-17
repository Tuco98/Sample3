package org.example.multithreading;

public class NumberPrinter {
    int num = 1;
    int limit;

    public NumberPrinter(int l){
        this.limit = l;
    }

    public synchronized void printOdd() throws InterruptedException {
        while (num <= limit){
            while (num%2 == 0){
                wait();
            }

            if (num<=limit){
                System.out.println(Thread.currentThread().getName()+": "+num);
                num++;
                notify();
            }
        }
    }

    public synchronized void printEven() throws InterruptedException {
        while (num <= limit){
            while (num%2 != 0){
                wait();
            }

            if (num<=limit){
                System.out.println(Thread.currentThread().getName()+": "+num);
                num++;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        NumberPrinter obj = new NumberPrinter(10);
        Thread t1 = new Thread(()->{
            try {
                obj.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"Odd");

        Thread t2 = new Thread(()->{
            try {
                obj.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"Even");

        t1.start();
        t2.start();
    }
}
