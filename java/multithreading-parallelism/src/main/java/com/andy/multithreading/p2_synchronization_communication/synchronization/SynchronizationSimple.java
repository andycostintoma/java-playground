package com.andy.multithreading.p2_synchronization_communication.synchronization;

/**
 * This class addresses the race condition issue by using the 'synchronized' keyword
 * to protect the shared counter.
 */

public class SynchronizationSimple {

    private static int counter = 0;

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The counter is: " + counter);
    }

    public static synchronized void increment() {
        counter++;
    }


    public static void main(String[] args) {
        process();
    }
}
