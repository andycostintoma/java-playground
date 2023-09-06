package com.andy.basics.completable_futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureCombiningTasks {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create two CompletableFuture instances
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 23);

        // Combine results using thenCombine (BiFunction<T, U, V>)
        CompletableFuture<Integer> combinedThenCombine = future1.thenCombine(future2, Integer::sum);

        // Combine results using thenAcceptBoth (BiConsumer<T, U>)
        CompletableFuture<Void> combinedThenAcceptBoth = future1.thenAcceptBoth(future2, (lhs, rhs) -> {
            System.out.println("Combined Result (thenAcceptBoth): " + (lhs + rhs));
        });

        // Combine results using runAfterBoth (Runnable)
        CompletableFuture<Void> combinedRunAfterBoth = future1.runAfterBoth(future2, () -> {
            System.out.println("Both tasks completed (runAfterBoth)");
        });

        // Apply a function to either of the results using applyToEither (Function<T, U>)
        CompletableFuture<Integer> applyToEither = future1.applyToEither(future2, result -> result * 2);

        // Accept the result of either task using acceptEither (Consumer<T>)
        CompletableFuture<Void> acceptEither = future1.acceptEither(future2, result -> {
            System.out.println("Result of either task (acceptEither): " + result);
        });

        // Run an action after either task using runAfterEither (Runnable)
        CompletableFuture<Void> runAfterEither = future1.runAfterEither(future2, () -> {
            System.out.println("Either task completed (runAfterEither)");
        });

        // Wait for all CompletableFuture instances to complete
        combinedThenCombine.thenAccept(result -> {
            System.out.println("Combined Result (thenCombine): " + result);
        }).join();

        combinedThenAcceptBoth.join();
        combinedRunAfterBoth.join();
        System.out.println("Result of applyToEither: " + applyToEither.get());
        acceptEither.join();
        runAfterEither.join();
    }
}
