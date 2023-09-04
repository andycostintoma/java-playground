package com.andy.multithreading.p2_synchronization_communication.synchronization;

/**
 * This class demonstrates the use of the same intrinsic lock for multiple synchronized methods.
 * It shows that when methods are synchronized using the same intrinsic lock, they can't execute concurrently.
 */

public class SynchronizationWithSameIntrinsicLock {

    public static final int LOOP_MAX = 10000;
    private static int counter1 = 0;
    private static int counter2 = 0;

    // if we use the same lock, the methods will not execute concurrently

    public static synchronized void increment1() {
            counter1++;

    }

    public static synchronized void increment2() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            counter2++;
        }

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < LOOP_MAX; i++) {
                increment1();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < LOOP_MAX; i++) {
                increment1();
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < LOOP_MAX; i++) {
                increment2();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            System.out.println("The counter1 is: " + counter1);

            t3.join();
            System.out.println("The counter2 is: " + counter2);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        process();
    }
}
