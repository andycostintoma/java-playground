package com.andy.completable_futures;

import java.util.concurrent.CompletableFuture;

/**
 * Example illustrating the use of CompletableFuture operations to combine tasks.
 */
public class CombiningCompletableFutures {

    public static void main(String[] args) {
        // Create two CompletableFuture instances.
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10);

        // Example 1: Combine the results of both CompletableFuture instances using thenCombine.
        // Uses BiFunction
        CompletableFuture<Integer> combinedResult1 = future1.thenCombine(future2, Integer::sum);
        System.out.println("Combined Result 1: " + combinedResult1.join());

        // Example 2: Use thenAcceptBoth to perform an action when both CompletableFuture instances complete.
        // Uses BiConsumer
        future1.thenAcceptBoth(future2, (result1, result2) -> {
            int sum = result1 + result2;
            System.out.println("Sum of results: " + sum);
        });

        // Example 3: Use runAfterBoth to run a task after both CompletableFuture instances complete.
        // Uses Runnable
        CompletableFuture<Void> runAfterBothExample = future1.runAfterBoth(future2, () -> {
            System.out.println("Both futures have completed.");
        });
        runAfterBothExample.join();

        // Example 4: Use applyToEither to process the result of the first CompletableFuture to complete.
        // Uses Function
        CompletableFuture<Integer> eitherResult = future1.applyToEither(future2, result -> result * 2);
        System.out.println("Result of the first CompletableFuture to complete: " + eitherResult.join());

        // Example 5: Use acceptEither to perform an action when either CompletableFuture completes.
        // Uses Consumer
        CompletableFuture<Void> acceptEitherExample = future1.acceptEither(future2, result -> {
            System.out.println("Either of the futures completed with result: " + result);
        });

        // Example 6: Use runAfterEither to run a task when either CompletableFuture completes.
        // Uses Runnable
        CompletableFuture<Void> runAfterEitherExample = future1.runAfterEither(future2, () -> {
            System.out.println("Either of the futures has completed.");
        });

        // Wait for the CompletableFuture examples to complete.
        acceptEitherExample.join();
        runAfterEitherExample.join();
    }
}
