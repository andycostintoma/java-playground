package com.andy.completable_futures;

import java.util.concurrent.CompletableFuture;

/**
 * <html>
 * Example illustrating the use of CompletableFuture's <strong>allOf</strong> and <strong>anyOf</strong> methods.
 * <p>
 * CompletableFuture provides methods for coordinating multiple CompletableFutures:
 * </p>
 * <p>
 * <strong>allOf</strong> - Waits for all specified CompletableFutures to complete.
 * </p>
 * <p>
 * <strong>anyOf</strong> - Waits for the first specified CompletableFuture to complete.
 * </p>
 * </html>
 */
public class CoordinatingMultipleCompletableFutures {

    public static void main(String[] args) {
        // Create three CompletableFuture instances.
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 23);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 10);

        // Example 1: Using allOf to wait for all CompletableFutures to complete.
        CompletableFuture<Void> allOfResult = CompletableFuture.allOf(future1, future2, future3);
        allOfResult.join();
        System.out.println("All CompletableFutures have completed.");

        // Example 2: Using anyOf to wait for the first CompletableFuture to complete.
        CompletableFuture<Object> anyOfResult = CompletableFuture.anyOf(future1, future2, future3);
        Object firstResult = anyOfResult.join();
        System.out.println("The first CompletableFuture to complete has a result: " + firstResult);
    }
}
