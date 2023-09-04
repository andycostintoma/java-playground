package com.andy.multithreading.p4_executors_futures.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture Advanced Example:
 *
 * <p><strong>Purpose:</strong> This example demonstrates advanced usage of CompletableFuture, including combining
 * multiple asynchronous tasks, handling exceptions, and adding timeouts to tasks.</p>
 *
 * <p><strong>Usage:</strong> CompletableFuture is used to perform complex asynchronous operations where you need to
 * coordinate multiple tasks and handle errors gracefully.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>Combining multiple CompletableFutures with thenCombine and thenApply methods.</li>
 *   <li>Handling exceptions with exceptionally and handle methods.</li>
 *   <li>Adding timeouts to tasks using completeOnTimeout.</li>
 * </ul>
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create a CompletableFuture for fetching user data asynchronously
        CompletableFuture<String> fetchUserData = CompletableFuture.supplyAsync(() -> {
            try {
                // Simulate fetching user data from an external service
                TimeUnit.SECONDS.sleep(2);
                return "User123";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Create a CompletableFuture for fetching user details asynchronously
        CompletableFuture<String> fetchUserDetails = CompletableFuture.supplyAsync(() -> {
            try {
                // Simulate fetching user details from a database
                TimeUnit.SECONDS.sleep(3);
                return "John Doe";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Combine the results of fetchUserData and fetchUserDetails
        CompletableFuture<String> combinedResult = fetchUserData.thenCombine(fetchUserDetails, (userId, userDetails) ->
                "User ID: " + userId + ", User Details: " + userDetails);

        // Handle exceptions that may occur during the computation
        CompletableFuture<String> exceptionHandling = fetchUserData
                .thenApplyAsync(userData -> {
                    if (userData.isEmpty()) {
                        throw new IllegalArgumentException("User data not found.");
                    }
                    return userData;
                })
                .exceptionally(throwable -> {
                    System.out.println("Exception occurred: " + throwable.getMessage());
                    return "Default User";
                });

        // Add a timeout to a CompletableFuture
        CompletableFuture<String> taskWithTimeout = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return "Task Completed";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).completeOnTimeout("Task Timed Out", 3, TimeUnit.SECONDS);

        // Wait for the CompletableFuture tasks to complete
        String result = combinedResult.get();
        String exceptionResult = exceptionHandling.get();
        String timeoutResult = taskWithTimeout.get();

        // Print the results
        System.out.println("Combined Result: " + result);
        System.out.println("Exception Handling Result: " + exceptionResult);
        System.out.println("Timeout Result: " + timeoutResult);
    }
}
