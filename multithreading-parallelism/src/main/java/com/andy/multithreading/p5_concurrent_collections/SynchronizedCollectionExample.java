package com.andy.multithreading.p5_concurrent_collections;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * <p><strong>Purpose:</strong> This class demonstrates the usage of synchronized collections, which provide a way to make standard
 * collection classes thread-safe by ensuring that their methods are synchronized, allowing multiple threads to safely access and modify the collection.</p>
 *
 * <p><strong>Usage:</strong> Synchronized collections are used when you need to ensure thread-safety while working with standard collections
 * such as ArrayList, HashMap, or HashSet in a multi-threaded environment.</p>
 *
 * <p><strong>Disadvantages:</strong></p>
 * <ul>
 *   <li><strong>Performance Overhead:</strong> Synchronization adds lock contention, potentially leading to performance bottlenecks in high-concurrency scenarios.</li>
 *   <li><strong>Limited Scalability:</strong> Synchronized collections protect the entire collection with a single lock, reducing concurrency and scalability.</li>
 *   <li><strong>Potential Deadlocks:</strong> Non-deterministic lock acquisition order can lead to circular wait conditions and deadlocks.</li>
 *   <li><strong>Lack of Fine-Grained Control:</strong> Fine-grained control over specific operations or subsets of the collection may require custom synchronization mechanisms.</li>
 *   <li><strong>Read-Write Operations:</strong> Synchronized collections may not be efficient when write operations are frequent compared to read operations.</li>
 * </ul>
 */
public class SynchronizedCollectionExample {
    public static void main(String[] args) {
        // Create an un-synchronized ArrayList
        List<Integer> unsynchronizedList = new ArrayList<>();

        // Wrap the un-synchronized ArrayList in a synchronized list
        List<Integer> synchronizedList = Collections.synchronizedList(unsynchronizedList);

        // Create multiple threads to concurrently modify the synchronized list
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedList.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                synchronizedList.add(i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Print the size of the synchronized list
        System.out.println("Size of synchronizedList: " + synchronizedList.size());
    }
}
