package com.andy.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeoutExample {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return 42;
        });

        // Use orTimeout to handle timeouts
        CompletableFuture<Integer> result = future.orTimeout(3, TimeUnit.SECONDS);

        result.thenAcceptAsync(value -> {
            System.out.println("Result: " + value);
        }).exceptionally(ex -> {
            System.err.println("Task threw an exception: " + ex.getMessage());
            return null;
        });

        // Wait for the result
        try {
            result.get();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
