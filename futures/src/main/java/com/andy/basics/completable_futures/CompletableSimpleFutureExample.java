package com.andy.basics.completable_futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableSimpleFutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Create a CompletableFuture representing an asynchronous task
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous computation
            System.out.println("(task) start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Simulate an error condition
            if (Math.random() < 0.5) {
                System.out.println("(task) failed");
                throw new RuntimeException("Task failed");
            }
            System.out.println("(task) done");
            return 42;
        });

        System.out.println("(main) before applying transformations");

        // Chain transformations to the future
        CompletableFuture<String> resultFuture = future
                .thenApplyAsync(result -> "Result: " + result)
                .thenApplyAsync(transformedResult -> transformedResult + " (additional transformation)")
                .exceptionally(ex -> "Error: " + ex.getMessage());

        System.out.println("(main) after applying transformations");

        // Wait for the result (blocking)
        String result = resultFuture.get();

        System.out.println("(main) after getting the result");
        System.out.println(result);
    }
}
