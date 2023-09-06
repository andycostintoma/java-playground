package com.andy.basics.completable_futures;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureComposingTasksExample {

    public static void main(String[] args) {
        // Simulate a URL to download content from
        String url = "https://example.com";

        CompletableFuture<String> downloadFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Step 1: Downloading content...");
            // Simulate downloading content (replace with actual download logic)
            return "Web content for " + url;
        });

        // thenApply (uses a Function)
        CompletableFuture<String> processFuture = downloadFuture.thenApply(content -> {
            System.out.println("Step 2: Cleaning and processing content...");
            // Simulate processing content (replace with actual processing logic)
            return "Processed content: " + content;
        });

        // thenAccept (uses a Consumer)
        CompletableFuture<Void> saveFuture = processFuture.thenAccept(processedContent -> {
            System.out.println("Step 3: Saving processed content...");
            // Simulate saving content (replace with actual saving logic)
            System.out.println("Content saved: " + processedContent);
        });

        // Using thenRunAsync to execute an action without a result (uses a Runnable)
        CompletableFuture<Void> finalTask = saveFuture.thenRunAsync(() -> {
            System.out.println("Final task: Workflow completed!");
            try {
                Thread.sleep(1000); // Simulate a delay
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        // Using thenRun to execute another action without a result (uses a Runnable)
        CompletableFuture<Void> anotherFinalTask = finalTask.thenRun(() -> {
            System.out.println("Another final task: Additional action!");
        });

        // Wait for the final tasks to complete
        anotherFinalTask.join();
    }
}
