package com.andy.rxjava.basics;

import java.util.concurrent.TimeUnit;

/**
 * <p> This example demonstrates the use of callbacks in Java for handling asynchronous operations.
 * It defines a simple asynchronous operation performed by the `AsyncOperation` class, and a callback
 * interface `Callback` to handle the result of the operation. </p>
 * <p> In this example, an asynchronous operation simulates fetching data from a remote server and
 * invokes the callback when the operation is completed. The main thread continues with other work
 * while waiting for the asynchronous operation to finish. </p>
 */
public class SimpleCallbackExample {

    @FunctionalInterface
    interface Callback {
        void onComplete(String result);
    }

    // Class that performs an asynchronous operation and invokes the callback when done
    static class AsyncOperation {
        void startAsyncOperation(Callback callback) {
            // Simulate an asynchronous operation (e.g., fetching data from a remote server)
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2); // Simulate a delay
                    String data = "Async operation completed!";
                    callback.onComplete(data);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        AsyncOperation asyncOperation = new AsyncOperation();

        // Define a callback implementation
        Callback callback = result -> System.out.println("Callback received: " + result);

        System.out.println("Starting async operation...");
        asyncOperation.startAsyncOperation(callback);

        // Continue with other work while waiting for the async operation
        System.out.println("Doing some other work...");

        // Sleep to allow time for the async operation to complete
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Main thread finished.");
    }
}
