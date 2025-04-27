package com.andy.completable_futures;

import java.util.concurrent.CompletableFuture;

/**
 * Example illustrating Exception handling with CompletableFuture and behavior of -Either operation.
 */
public class ExceptionHandlinginCompletableFutures {

    public static void main(String[] args) {
        // Example 1: Handling exceptions with exceptionally
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> exceptionalFuture = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("An exception occurred.");
        });

        CompletableFuture<Integer> result1 = future1.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return -1; // Recovery value
        });

        CompletableFuture<Integer> result2 = exceptionalFuture.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return -1; // Recovery value
        });

        System.out.println("Result 1: " + result1.join()); // Result 1: 42
        System.out.println("Result 2: " + result2.join()); // Result 2: -1

        // Example 2: Handling exceptions with a handle
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> exceptionFuture2 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Another exception occurred.");
        });

        CompletableFuture<Integer> handleResult1 = future3.handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Exception occurred: " + ex.getMessage());
                return -1; // Recovery value
            }
            return result;
        });

        CompletableFuture<Integer> handleResult2 = exceptionFuture2.handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Exception occurred: " + ex.getMessage());
                return -1; // Recovery value
            }
            return result;
        });

        System.out.println("Handle Result 1: " + handleResult1.join()); // Handle Result 1: 42
        System.out.println("Handle Result 2: " + handleResult2.join()); // Handle Result 2: -1

        // Example 3: Using whenComplete to handle both success and failure
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> exceptionFuture3 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Yet another exception occurred.");
        });

        future4.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Exception occurred: " + ex.getMessage());
            } else {
                System.out.println("Result without exception: " + result);
            }
        });

        exceptionFuture3.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Exception occurred: " + ex.getMessage());
            } else {
                System.out.println("Result without exception: " + result);
            }
        });

        // Example 4: Using Either operation with rejected stages
        CompletableFuture<String> notFailed =
                CompletableFuture.supplyAsync(() -> "Success!");

        CompletableFuture<String> failed =
                CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Another exception occurred.");
                });

        var rejected = failed.acceptEither(notFailed, System.out::println); // No output due to rejected previous stage
        var resolved = notFailed.acceptEither(failed, System.out::println); // Output: Success!

        // Wait for all CompletableFuture tasks to complete.
        try {
            CompletableFuture.allOf(result1, result2, handleResult1, handleResult2, rejected, resolved).join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
