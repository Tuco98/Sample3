package org.example.practice.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintedModern {
    boolean thread1Turn = true;
    int count1 = 1;
    int count2 = 1;
    int k;
    int n;
    Lock lock = new ReentrantLock();
    Condition thread1Condition = lock.newCondition();
    Condition thread2Condition = lock.newCondition();

    public PrintedModern(int k, int n) {
        this.k = k;
        this.n = n;
    }

    public void f1(){

        while (count1<=n){
            lock.lock();;

            try {

                while (!thread1Turn)
                    thread1Condition.await();

                System.out.print("Thread1: ");

                for (int i = 0; i < k && count1 <= n; i++) {
                    System.out.print(" "+count1++);
                }

                System.out.println();
                thread1Turn = false;
                thread2Condition.signal();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

    }

    public void f2(){

        while (count2<=n){
            lock.lock();;

            try {

                while (thread1Turn)
                    thread2Condition.await();

                System.out.print("Thread2: ");

                for (int i = 0; i < k && count2 <= n; i++) {
                    System.out.print(" "+count2++);
                }

                System.out.println();
                thread1Turn = true;
                thread1Condition.signal();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

    }
}
