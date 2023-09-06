package com.andy.basics.completable_futures;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCreationExample {

    public static void main(String[] args) {
        // Create a CompletableFuture for a runnable task (no result)
        CompletableFuture<Void> runnableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Running an asynchronous task (runAsync)");
            // Simulate some work
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        // Create a CompletableFuture for a supplier task (with a result)
        CompletableFuture<String> supplierFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Running an asynchronous task (supplyAsync)");
            // Simulate some work
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return "Task result";
        });

        // Perform other tasks while CompletableFuture tasks are running asynchronously
        System.out.println("Main thread continues to work...");

        // Wait for the completion of the supplierFuture and retrieve the result
        try {
            String result = supplierFuture.get(); // Blocking until the result is available
            System.out.println("Result from CompletableFuture: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Wait for the runnableFuture to complete (no result)
        try {
            runnableFuture.get(); // Blocking until the task is complete
            System.out.println("Runnable CompletableFuture has completed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
