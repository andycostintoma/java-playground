package com.andy.completable_futures;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility class for working with CompletableFuture instances.
 */
public final class CompletableFuturesUtils {

    @SafeVarargs
    private static <T> Function<Void, List<T>> gatherResults(CompletableFuture<T>... cfs) {
        return unused -> Arrays.stream(cfs)
                .filter(Predicate.not(CompletableFuture::isCompletedExceptionally))
                .map(CompletableFuture::join)
                .toList();
    }

    /**
     * Combines multiple CompletableFuture instances, waiting for all to complete.
     *
     * @param cfs An array of CompletableFuture instances.
     * @param <T> The type of the CompletableFuture results.
     * @return A CompletableFuture containing a list of completed results.
     */
    @SafeVarargs
    public static <T> CompletableFuture<List<T>> eachOf(CompletableFuture<T>... cfs) {
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfs);
        return allOf.thenApply(gatherResults(cfs));
    }

    /**
     * Combines multiple CompletableFuture instances, waiting for all to complete.
     * If any CompletableFuture completes exceptionally, it is ignored.
     *
     * @param cfs An array of CompletableFuture instances.
     * @param <T> The type of the CompletableFuture results.
     * @return A CompletableFuture containing a list of completed results (excluding exceptions).
     */
    @SafeVarargs
    public static <T> CompletableFuture<List<T>> bestEffort(CompletableFuture<T>... cfs) {
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfs);
        return allOf.exceptionally(ex -> null)
                .thenApply(gatherResults(cfs));
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 11);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 22);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 33);
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Future 4 failed"); // Simulate an exception
        });

        CompletableFuture<List<Integer>> result1 = CompletableFuturesUtils.eachOf(future1, future2, future3);
        CompletableFuture<List<Integer>> result2 = CompletableFuturesUtils.bestEffort(future1, future2, future3, future4);

        // Wait for the results and print them
        try {
            List<Integer> resultList1 = result1.get();
            List<Integer> resultList2 = result2.get();
            System.out.println("Results from eachOf: " + resultList1);
            System.out.println("Results from bestEffort: " + resultList2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
