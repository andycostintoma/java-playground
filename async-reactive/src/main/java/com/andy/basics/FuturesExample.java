package com.andy.basics;

import java.util.concurrent.*;

/**
 * This example illustrates the Future-style API in Java.
 * It demonstrates how to use Future objects to perform asynchronous computations.
 */
public class FuturesExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Create a ThreadPoolExecutor with two worker threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Submit two tasks that return Futures
        Future<Integer> futureResult1 = executorService.submit(() -> {
            // Simulate a time-consuming computation
            Thread.sleep(2000); // Sleep for 2 seconds
            return 42;
        });

        Future<Integer> futureResult2 = executorService.submit(() -> {
            // Another time-consuming computation
            Thread.sleep(1000); // Sleep for 1 second
            return 24;
        });

        // Perform other work while the computations are in progress
        System.out.println("Doing some other work...");

        // Wait for the first computation to complete and retrieve its result
        int result1 = futureResult1.get();

        // Wait for the second computation to complete and retrieve its result
        int result2 = futureResult2.get();

        // Shutdown the executor
        executorService.shutdown();

        // Process the results
        int sum = result1 + result2;
        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);
        System.out.println("Sum of results: " + sum);
    }
}
