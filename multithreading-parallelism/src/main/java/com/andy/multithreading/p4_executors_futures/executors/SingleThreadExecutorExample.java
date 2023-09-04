package com.andy.multithreading.p4_executors_futures.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p><strong>Purpose:</strong> A SingleThreadExecutor is an ExecutorService that manages a single worker thread to execute
 * tasks sequentially in a single thread. It is useful for scenarios where you want to ensure that tasks
 * are executed one at a time in a predictable order.</p>
 *
 * <p><strong>Usage:</strong> SingleThreadExecutors are suitable for tasks that should not be executed concurrently, such as
 * maintaining the state of a shared resource or processing a queue of tasks sequentially.</p>
 *
 * <p><strong>Key Concepts:</strong></h2>
 *
 * <ul>
 *   <li>SingleThreadExecutor manages a single worker thread to execute tasks one by one.</li>
 *   <li>Tasks submitted to a SingleThreadExecutor are executed sequentially in the order they are submitted.</li>
 *   <li>The executor ensures that there is no concurrent execution of tasks by using a single thread.</li>
 *   <li>If a task encounters an exception and terminates, a new task is automatically scheduled to replace it.</li>
 *   <li>SingleThreadExecutor provides a simple and predictable execution environment.</li>
 * </ul>
 */
public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        // Create a SingleThreadExecutor with a single worker thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit tasks to the executor
        for (int i = 1; i <= 5; i++) {
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
