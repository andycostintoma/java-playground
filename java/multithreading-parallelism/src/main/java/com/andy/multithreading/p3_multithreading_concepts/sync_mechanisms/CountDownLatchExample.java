package com.andy.multithreading.p3_multithreading_concepts.sync_mechanisms;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * <p><strong>Purpose:</strong> This example demonstrates the use of CountDownLatch, which is a synchronization aid that allows one or more threads to wait for a set of operations to complete before proceeding.</p>
 *
 * <p><strong>Usage:</strong> CountDownLatch is useful in scenarios where you have multiple threads performing tasks, and you want one or more threads to wait until all the tasks are completed before they proceed with their work.</p>
 */
public class CountDownLatchExample {

    public static void main(String[] args) {
        int numWorkers = 3; // Number of worker threads
        int latchCount = 3; // CountDownLatch count

        CountDownLatch latch = new CountDownLatch(latchCount);

        // Create worker threads
        for (int i = 1; i <= numWorkers; i++) {
            Worker worker = new Worker(latch, "Worker " + i);
            worker.start();
        }

        try {
            // Main thread waits for some workers to finish
            latch.await();
            System.out.println("Count: " + latch.getCount());
            System.out.println("Some workers have completed their tasks. Main thread continues.");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    static class Worker extends Thread {
        private final CountDownLatch latch;
        private final Random random = new Random();

        public Worker(CountDownLatch latch, String name) {
            super(name);
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // Simulate random work duration between 1 and 5 seconds
                int workDuration = random.nextInt(5) + 1;
                Thread.sleep(workDuration * 1000);
                System.out.println(getName() + " has completed its task after " + workDuration + " seconds.");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                // Signal that this worker has completed its task
                latch.countDown();
            }
        }
    }
}
