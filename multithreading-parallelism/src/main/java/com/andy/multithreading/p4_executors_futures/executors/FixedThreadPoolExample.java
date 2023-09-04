package com.andy.multithreading.p4_executors_futures.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p><strong>Purpose:</strong> A FixedThreadPool is an ExecutorService that manages a fixed number of worker threads
 * to execute tasks concurrently. It is useful when you want to limit the number of active threads and control
 * resource usage.</p>
 *
 * <p><strong>Usage:</strong> FixedThreadPools are suitable for scenarios where you have a specific number of tasks
 * to execute concurrently, such as parallel processing of data or handling a fixed number of client requests.</p>
 *
 * <p><strong>Key Concepts:</strong></h2>
 * <ul>
 *   <li>FixedThreadPool maintains a fixed number of worker threads that are ready to execute tasks.</li>
 *   <li>Tasks submitted to a FixedThreadPool are executed concurrently by the available threads.</li>
 *   <li>If all threads are busy when a new task is submitted, the task waits in the queue until a thread becomes available.</li>
 *   <li>FixedThreadPools are efficient for managing thread reuse and preventing excessive resource consumption.</li>
 * </ul>
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        int threadCount = 3; // Number of threads in the fixed pool
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // Submit tasks to the executor
        for (int i = 1; i <= 10; i++) {
            int taskNumber = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskNumber + " is running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task execution time
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Task " + taskNumber + " completed.");
            });
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }
}
