package com.andy.multithreading.p3_multithreading_concepts.sync_mechanisms;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p><strong>Purpose:</strong> This example demonstrates the use of CyclicBarrier, which is a
 * synchronization aid that allows a set of threads to wait for each other to reach a common
 * barrier point before proceeding.</p>
 *
 * <p><strong>Usage:</strong> CyclicBarrier is useful when you have multiple threads performing tasks,
 * and you want them to synchronize at certain points in their execution before moving forward together.</p>
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        int numThreads = 3; // Number of threads
        Runnable barrierAction = () -> System.out.println("All threads have reached the barrier.");

        // Create a CyclicBarrier with the specified number of parties and a barrier action
        CyclicBarrier barrier = new CyclicBarrier(numThreads, barrierAction);

        // Create and start the worker threads
        for (int i = 1; i <= numThreads; i++) {
            Worker worker = new Worker(barrier, "Thread " + i);
            worker.start();
        }
    }

    static class Worker extends Thread {
        private final CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier, String name) {
            super(name);
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " is performing its task.");
                // Simulate some work
                Thread.sleep(2000);
                System.out.println(getName() + " has finished its task and is waiting at the barrier.");
                // Wait at the barrier
                barrier.await();
                System.out.println(getName() + " continues after the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
