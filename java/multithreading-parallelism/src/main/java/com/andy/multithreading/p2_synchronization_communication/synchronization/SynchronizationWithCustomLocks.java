package com.andy.multithreading.p2_synchronization_communication.synchronization;

/**
 * This class demonstrates the use of custom locks (objects) to synchronize access to different methods.
 * It shows that you can use different locks to allow concurrent execution of unrelated methods.
 */

public class SynchronizationWithCustomLocks {

    public static final int LOOP_MAX = 10000;
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    public static void increment2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            counter2++;
        }
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
