package com.andy.multithreading.p4_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p><strong>Purpose:</strong> A CachedThreadPool is a type of thread pool in which the number of threads can grow or shrink
 * dynamically based on the workload. Threads are created and reused as needed.</p>
 *
 * <p><strong>Usage:</strong> CachedThreadPool is suitable for scenarios where you have a large number of short-lived tasks,
 * and you want to efficiently manage thread creation and recycling.</p>
 *
 * <p><strong>Key Concepts:</strong></h2>
 * <ul>
 *   <li>CachedThreadPool creates new threads as needed, and reuses them when they become available.</li>
 *   <li>Threads that remain idle for a certain period are terminated and removed from the pool.</li>
 *   <li>This pool is suitable for tasks with variable workloads and short durations.</li>
 *   <li>It can create and discard threads dynamically to accommodate incoming tasks.</li>
 * </ul>
 */
public class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // Simulate a series of short-lived tasks
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task execution time
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }
}
