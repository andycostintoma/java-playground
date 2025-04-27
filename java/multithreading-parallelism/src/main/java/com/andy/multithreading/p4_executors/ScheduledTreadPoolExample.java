package com.andy.multithreading.p4_executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p><strong>Purpose:</strong> A ScheduledExecutor is an ExecutorService that can schedule tasks for execution at a fixed
 * rate or with a fixed delay. It is useful for scenarios where you need to perform periodic or delayed tasks.</p>
 *
 * <p><strong>Usage:</strong> ScheduledExecutors are suitable for tasks like running scheduled jobs, periodic data updates,
 * or implementing timers and timeouts.</p>
 *
 * <p><strong>Key Concepts:</strong></h2>
 * <ul>
 *   <li>ScheduledExecutorService provides methods to schedule tasks with fixed rates or delays.</li>
 *   <li>You can schedule tasks to run at specified intervals or after a fixed delay.</li>
 *   <li>Tasks scheduled with ScheduledExecutor are executed by worker threads from the pool.</li>
 *   <li>It simplifies the management of periodic tasks without manual thread management.</li>
 * </ul>
 */
public class ScheduledTreadPoolExample {

    public static void main(String[] args) {

        int poolSize = 2; // Number of threads in the pool
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);

        // Schedule a task to run with an initial delay and repeat every 2 seconds
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Task 1 is running on thread: " + Thread.currentThread().getName());
        }, 1, 2, TimeUnit.SECONDS);

        // Schedule another task to run with a fixed delay after the previous task completes
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("Task 2 is running on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // Simulate task execution time
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }, 3, 2, TimeUnit.SECONDS);

        // Allow the scheduled tasks to run for some time
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }
}







