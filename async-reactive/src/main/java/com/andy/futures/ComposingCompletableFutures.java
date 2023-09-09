package com.andy.futures;

import java.util.concurrent.CompletableFuture;

/**
 * Example illustrating the use of thenAccept, thenRun, and thenApply with CompletableFuture.
 */
public class ComposingCompletableFutures {

    public static void main(String[] args) {
        // Create an initial CompletableFuture with some data.
        CompletableFuture<Integer> initialFuture = CompletableFuture.supplyAsync(() -> 42);

        // Define a chain of processing steps using thenApply, thenRun, and thenAccept.
        CompletableFuture<Void> resultFuture = initialFuture
                .thenApply(data -> {
                    System.out.println("ThenApply Method in " + Thread.currentThread().getName());
                    return data * 2;
                })
                .thenAccept(processedData -> {
                    System.out.println("ThenAccept Method in " + Thread.currentThread().getName());
                    System.out.println("Processed Data: " + processedData);
                })
                .thenRun(() -> {
                    System.out.println("ThenRun Method in " + Thread.currentThread().getName());
                    System.out.println("Data processed successfully.");
                });


        // Wait for the resultFuture to complete.
        resultFuture.join();
    }
}

