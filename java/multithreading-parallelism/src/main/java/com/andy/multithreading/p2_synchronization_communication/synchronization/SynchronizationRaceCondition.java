package com.andy.multithreading.p2_synchronization_communication.synchronization;

/**
 * This class demonstrates a race condition scenario where two threads increment a shared counter variable
 * without synchronization.
 */

public class SynchronizationRaceCondition {
    private static int counter = 0;

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
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


    public static void main(String[] args) {
        process();
    }
}
