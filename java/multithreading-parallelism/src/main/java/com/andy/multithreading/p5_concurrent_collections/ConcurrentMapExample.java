package com.andy.multithreading.p5_concurrent_collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p><strong>ConcurrentMap Example:</strong></p>
 *
 * <p><strong>Purpose:</strong> ConcurrentMap is an interface that extends the Map interface and provides thread-safe operations for concurrent access to a map data structure.</p>
 *
 * <p><strong>Usage:</strong> ConcurrentMap is useful in scenarios where multiple threads need to access and modify a map concurrently without explicit synchronization. It allows for safe, concurrent access to the map while maintaining performance.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>ConcurrentMap provides atomic operations for common map operations like put, get, remove, and more.</li>
 *   <li>It supports thread-safe access and updates, ensuring consistency in a multi-threaded environment.</li>
 *   <li>ConcurrentMap implementations like ConcurrentHashMap are optimized for concurrent access and provide high performance.</li>
 * </ul>
 */

public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Create a producer thread that puts elements into the map
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    String key = "Key" + i;
                    int value = i * 10;
                    concurrentMap.put(key, value);
                    System.out.println("Put: " + key + " -> " + value);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();

        // Create a consumer thread that reads elements from the map
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(2000);
                    String key = "Key" + i;
                    int value = concurrentMap.getOrDefault(key, -1);
                    System.out.println("Get: " + key + " -> " + value);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
}
