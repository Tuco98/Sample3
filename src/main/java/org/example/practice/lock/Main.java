package org.example.practice.lock;

public class Main {
    public static void main(String[] args) {
//        Printer printer = new Printer(5,10);
        PrintedModern printer = new PrintedModern(5,10);
        Thread t1 = new Thread(printer::f1);

        Thread t2 = new Thread(printer::f2);

        t1.start();
        t2.start();
    }
}
