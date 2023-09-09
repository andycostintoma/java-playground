package com.andy.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Example demonstrating the creation and usage of CompletableFuture instances.
 */
public class CreatingCompletableFutures {

    public static void main(String[] args) {
        // Create a CompletableFuture to run a Runnable asynchronously (no result).
        CompletableFuture<Void> completableFutureRunnable = CompletableFuture.runAsync(() -> {
            // This task doesn't return a value, just prints a message.
            System.out.println("Async task (Runnable) is running...");
        });

        // Create a CompletableFuture to supply a result asynchronously.
        CompletableFuture<String> completableFutureSupplier = CompletableFuture.supplyAsync(() -> {
            // This task returns a greeting message.
            return "Hello, Async World!";
        });

        // Wait for the completable futures to complete and print the results.
        try {
            completableFutureRunnable.get(); // Wait for completion (no result expected).
            String message = completableFutureSupplier.get(); // Wait for completion and get the result.
            System.out.println(message);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
