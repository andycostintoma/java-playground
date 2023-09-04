package com.andy.multithreading.p3_multithreading_concepts;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class demonstrates the use of atomic variables to ensure thread safety.
 * Atomic variables guarantee that operations on them are atomic and thread-safe without the need for locks.
 */

public class AtomicVariablesExample {

    public static final int LOOP_MAX = 10000;
    // Create an atomic integer to ensure atomic and thread-safe operations.
    private static final AtomicInteger counter = new AtomicInteger(0);

    // Increment the atomic counter in a loop.
    public static void increment() {
        for (int i = 0; i < LOOP_MAX; i++) {
            counter.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        // Create two threads that increment the atomic counter concurrently.
        Thread t1 = new Thread(AtomicVariablesExample::increment);
        Thread t2 = new Thread(AtomicVariablesExample::increment);

        t1.start();
        t2.start();

        try {
            // Wait for both threads to finish.
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Print the final value of the atomic counter.
        System.out.println("Counter: " + counter);
    }
}
