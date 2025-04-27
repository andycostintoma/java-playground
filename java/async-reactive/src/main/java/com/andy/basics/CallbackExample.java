package com.andy.basics;

import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.function.Consumer;

/**
 * This example demonstrates a reactive-style API using callbacks for file processing.
 * It simulates asynchronous file processing with callbacks to handle the results.
 */
public class CallbackExample {

    private static final Random random = new Random();

    // higher order function that takes another function as an argument
    public static void processFile(String fileName, Consumer<String> callback) {
        // Simulate asynchronous file processing
        new Thread(() -> {
            try {
                System.out.println("Started processing " + fileName);
                // Simulate random processing duration between 1 and 3 seconds
                int sleepDuration = random.nextInt(3) + 1;
                TimeUnit.SECONDS.sleep(sleepDuration);
                String result = "Processed content of " + fileName; // Simulate processing
                callback.accept(result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }


    public static void main(String[] args) throws InterruptedException {


        // Simulate processing two files asynchronously with callbacks
        processFile("file1.txt", result -> {
            System.out.println("Result: " + result);
        });

        processFile("file2.txt", result -> {
            System.out.println("Result: " + result);
        });

        // Simulate other work while processing files
        System.out.println("Doing some other work...");

        // Sleep to keep the program running
        TimeUnit.SECONDS.sleep(5); // Sleep for 5 seconds
    }
}
