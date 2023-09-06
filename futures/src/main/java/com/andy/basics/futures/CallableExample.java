package com.andy.basics.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p><strong>Purpose:</strong> Callable is an interface that represents a task that can be executed asynchronously and
 * returns a result or throws an exception. It is used when you need to obtain a result or handle exceptions from a task.</p>
 *
 * <p><strong>Usage:</strong> Callable is suitable for tasks where you want to retrieve a result, manage exceptions, or
 * perform computations that may take some time.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>Callable is similar to Runnable but can return a result or throw an exception.</li>
 *   <li>Callable tasks are executed using an ExecutorService, which allows for thread management and result retrieval.</li>
 *   <li>The Future interface represents the result of a Callable task and provides methods to retrieve the result or handle exceptions.</li>
 * </ul>
 */
public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Define a Callable task that returns a result
        Callable<Integer> task = () -> {
            System.out.println("Callable task is running...");
            Thread.sleep(2000); // Simulate task execution time
            return 42; // Return a result
        };

        // Submit the Callable task for execution
        Future<Integer> future = executorService.submit(task);

        // Perform other tasks while waiting for the result
        System.out.println("Doing other work...");

        try {
            // Wait for the result and retrieve it
            Integer result = future.get();
            System.out.println("Callable task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }
}
