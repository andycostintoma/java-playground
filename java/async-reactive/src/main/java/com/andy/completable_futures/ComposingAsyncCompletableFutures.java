package com.andy.completable_futures;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Example illustrating the use of thenApplyAsync, thenAcceptAsync, and thenRunAsync with CompletableFuture.</p>
 * <p> These methods are asynchronous variants of thenApply, thenAccept, and thenRun. The non-Async methods execute their
 * task in the same thread as the previous task, even though that’s not guaranteed. The -Async variants will use a new
 * thread, created by the common ForkJoinPool or by the provided Executor.</p>
 */
public class ComposingAsyncCompletableFutures {

    public static void main(String[] args) {
        // Create an initial CompletableFuture with some data.
        CompletableFuture<Integer> initialFuture = CompletableFuture.supplyAsync(() -> 42);

        // Define a chain of processing steps using thenApplyAsync, thenAcceptAsync, and thenRunAsync.
        CompletableFuture<Void> resultFuture = initialFuture
                .thenApplyAsync(data -> {
                    System.out.println("ThenApplyAsync Method in " + Thread.currentThread().getName());
                    return data * 2;
                })
                .thenAcceptAsync(processedData -> {
                    System.out.println("ThenAcceptAsync Method in " + Thread.currentThread().getName());
                    System.out.println("Processed Data: " + processedData);
                })
                .thenRunAsync(() -> {
                    System.out.println("ThenRunAsync Method in " + Thread.currentThread().getName());
                    System.out.println("Data processed successfully.");
                });

        // Wait for the resultFuture to complete.
        resultFuture.join();
    }
}
