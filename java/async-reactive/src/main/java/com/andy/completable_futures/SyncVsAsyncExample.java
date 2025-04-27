package com.andy.completable_futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SyncVsAsyncExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Synchronous (Blocking) Example
        System.out.println("Synchronous Example:");
        int syncResult = longRunningTask();
        System.out.println("Synchronous Result: " + syncResult);

        // Asynchronous (Non-blocking) Example
        System.out.println("\nAsynchronous Example:");
        CompletableFuture<Integer> asyncFuture = CompletableFuture.supplyAsync(SyncVsAsyncExample::longRunningTask);

        System.out.println("Doing other work while waiting for the asynchronous task...");

        int asyncResult = asyncFuture.get();
        System.out.println("Asynchronous Result: " + asyncResult);

    }

    public static int longRunningTask() {
        // Simulate a time-consuming operation (e.g., database query)
        try {
            System.out.println("Long Running task...");
            Thread.sleep(1000); // Sleep for 2 seconds to simulate work
        } catch (InterruptedException e) {
            String message = e.getMessage();
            System.out.println(message);
        }

        return 42; // Return a result
    }
}
