package com.andy.futures;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Example demonstrating the use of thenCombine and thenCompose to unwrap nested stages.
 */
public class UnwrappingCompletableFutures {

    public static void main(String[] args) {
        // Create two CompletableFuture instances.
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 23);

        // Define a task that combines the results of future1 and future2 into another CompletableFuture.
        BiFunction<Integer, Integer, CompletableFuture<Integer>> task = (lhs, rhs)
                -> CompletableFuture.supplyAsync(() -> lhs + rhs);

        // Use thenCombine to combine the results of future1 and future2, resulting in a nested CompletableFuture.
        CompletableFuture<CompletableFuture<Integer>> nestedCombined = future1.thenCombine(future2, task);

        // Use thenCompose with Function.identity() to unwrap the nested CompletableFuture.
        CompletableFuture<Integer> combined = nestedCombined.thenCompose(Function.identity());

        // Print the final combined result.
        System.out.println("Combined Result: " + combined.join());
    }
}
