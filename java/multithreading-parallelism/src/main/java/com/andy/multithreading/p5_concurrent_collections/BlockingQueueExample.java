package com.andy.multithreading.p5_concurrent_collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p><strong>Purpose:</strong> This class demonstrates the usage of a BlockingQueue, which is a
 * thread-safe data structure that provides blocking operations for producer-consumer scenarios.
 * It allows multiple threads to exchange data efficiently and safely.</p>
 *
 * <p><strong>Usage:</strong> BlockingQueue is commonly used in scenarios where one or more producer
 * threads generate data, and one or more consumer threads consume that data concurrently,
 * ensuring synchronization and coordination without explicit locking.</p>
 */
public class BlockingQueueExample {
    public static void main(String[] args) {
        // Create a BlockingQueue with a maximum capacity of 5
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);

        // Create a producer thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    blockingQueue.put(i); // Put data into the queue (blocking if full)
                    System.out.println("Produced: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        // Create a consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int item = blockingQueue.take(); // Take data from the queue (blocking if empty)
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        // Start producer and consumer threads
        producerThread.start();
        consumerThread.start();

        try {
            // Wait for both threads to complete
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Finished producing and consuming.");
    }
}
