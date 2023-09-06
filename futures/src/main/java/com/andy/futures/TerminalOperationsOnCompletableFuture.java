package com.andy.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Example illustrating terminal operations with CompletableFuture.
 */
public class TerminalOperationsOnCompletableFuture {

    public static void main(String[] args) {
        // Create a CompletableFuture with a delayed result
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate a time-consuming task
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return 42;
        });

        try {
            // Terminal Operation 1: get()
            int result1 = future.get();
            System.out.println("Result from get(): " + result1);

            // Terminal Operation 2: get (long timeout, TimeUnit unit)
            int result2 = future.get(1, TimeUnit.SECONDS);
            System.out.println("Result from get(timeout): " + result2);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

        // Terminal Operation 3: getNow(T valueIfAbsent)
        int result3 = future.getNow(-1);
        System.out.println("Result from getNow(): " + result3);

        // Terminal Operation 4: join()
        int result4 = future.join();
        System.out.println("Result from join(): " + result4);

        // Terminal Operation 5: cancel (boolean mayInterruptIfRunning)
        boolean cancelled = future.cancel(false);
        System.out.println("Cancelled: " + cancelled);

        // Terminal Operation 6: isCancelled()
        boolean isCancelled = future.isCancelled();
        System.out.println("isCancelled: " + isCancelled);

        // Terminal Operation 7: isDone()
        boolean isDone = future.isDone();
        System.out.println("isDone: " + isDone);

        // Terminal Operation 8: isCompletedExceptionally()
        boolean completedExceptionally = future.isCompletedExceptionally();
        System.out.println("Completed Exceptionally: " + completedExceptionally);
    }
}
