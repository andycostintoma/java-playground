package com.andy.rxjava.basics;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FutureCallbackExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Starting async operation...");

        CompletableFuture<String> asyncFuture = CompletableFuture.supplyAsync(FutureCallbackExample::longRunningTask);

        // Continue with other work while waiting for the async operation
        System.out.println("Doing some other work...");

        // Handle the result when the async operation is completed
        asyncFuture.thenAccept(result -> {
            System.out.println("Callback received: " + result);
            System.out.println("Main thread finished.");
        });

        // Sleep to allow time for the async operation to complete
        TimeUnit.SECONDS.sleep(3);
    }

    public static String longRunningTask() {
        // Simulate a time-consuming operation (e.g., fetching data from a remote server)
        try {
            TimeUnit.SECONDS.sleep(2); // Simulate a delay
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return "Async operation completed!";
    }
}
