package com.andy.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p><strong>Future Example:</strong></p>
 *
 * <p><strong>Purpose:</strong> The Future interface represents the result of an asynchronous computation and provides
 * a way to retrieve the result or handle exceptions when the computation is complete.</p>
 *
 * <p><strong>Usage:</strong> Futures are commonly used when you have multiple asynchronous tasks and want to retrieve
 * their results in a coordinated manner.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>Future objects represent the result of asynchronous computations.</li>
 *   <li>You can submit Callable tasks to an ExecutorService and obtain Future objects for each task.</li>
 *   <li>You can use Future's methods like get() and isDone() to retrieve results and check completion status.</li>
 *   <li>Futures provide a way to wait for multiple tasks to complete using methods like invokeAll() and invokeAny().</li>
 * </ul>
 */
public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Create a list of Callable tasks
        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(() -> {
            Thread.sleep(2000);
            return 42;
        });
        tasks.add(() -> 100);
        tasks.add(() -> {
            Thread.sleep(1500);
            return 10;
        });

        try {
            // Submit all tasks and obtain a list of Future objects
            List<Future<Integer>> futures = executorService.invokeAll(tasks);

            // Wait for each task to complete and retrieve the results
            for (Future<Integer> future : futures) {
                if (future.isDone()) {
                    Integer result = future.get();
                    System.out.println("Task result: " + result);
                } else {
                    System.out.println("Task is not yet complete.");
                }
            }

            // Choose the first completed task and retrieve its result
            Integer firstCompletedResult = executorService.invokeAny(tasks);
            System.out.println("First completed task result: " + firstCompletedResult);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }
}
