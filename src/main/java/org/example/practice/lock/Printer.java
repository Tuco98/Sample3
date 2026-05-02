package org.example.practice.lock;

public class Printer {
    boolean thread1Turn = true;
    int k; //no of threads
    int n; //no till which i have to print

    public Printer(int k, int n) {
        this.k = k;
        this.n = n;
    }



    public synchronized void f1() throws InterruptedException {
        int count = 1;

        while (count <= n) {

            while (!thread1Turn)
                wait();

            System.out.print("Thread1: ");

            for (int i = 0; i < k && count <= n; i++) {
                System.out.print(" "+count++);
            }

            System.out.println();

            thread1Turn = false;
            notifyAll();
        }
    }

    public synchronized void f2() throws InterruptedException {

        int count = 1;

        while (count <= n) {

            while (thread1Turn)
                wait();
            System.out.print("Thread2: ");

            for (int i = 0; i < k && count <= n; i++) {
                System.out.print(" "+count++);
            }

            System.out.println();

            thread1Turn = true;
            notifyAll();
        }
    }
}
