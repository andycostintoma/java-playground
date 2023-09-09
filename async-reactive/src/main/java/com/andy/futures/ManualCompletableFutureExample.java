package com.andy.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Example illustrating manual creation and completion of CompletableFuture instances.
 */
public class ManualCompletableFutureExample {


    public static void main(String[] args) {
        // Manual Creation
        CompletableFuture<String> unsettled = new CompletableFuture<>();

        // Manually Complete
        unsettled.complete("Manually Completed Value");

        CompletableFuture<String> manuallyCompletedFuture = new CompletableFuture<>();
        manuallyCompletedFuture.completeExceptionally(new RuntimeException("Manually Completed Exception"));

        System.out.println("Using Manually Created CompletableFuture: " + unsettled.join());
        System.out.println("Using Manually Completed CompletableFuture: " + handleException(manuallyCompletedFuture));

        // Manual Completion with async variants (Java 9+)
        CompletableFuture<Integer> asyncCompletableFuture = new CompletableFuture<>();
        asyncCompletableFuture.completeAsync(ManualCompletableFutureExample::computeValueAsync, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        System.out.println("Using Manually Completed CompletableFuture with Async: " + asyncCompletableFuture.join());

        // Already Completed Futures
        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Already Completed Value");
        CompletableFuture<Integer> failedFuture = CompletableFuture.failedFuture(new RuntimeException("Already Completed Exception"));

        System.out.println("Using Already Completed CompletableFuture: " + completedFuture.join());
        System.out.println("Using Failed CompletableFuture: " + handleException(failedFuture));
    }


    private static <T> String handleException(CompletableFuture<T> future) {
        return (String) future.exceptionally(ex -> (T) ("Exception occurred: " + ex.getMessage())).join();
    }

    private static int computeValueAsync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 42;
    }
}
