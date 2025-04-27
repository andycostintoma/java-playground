package com.andy.multithreading.p5_concurrent_collections;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p><strong>CopyOnWriteArrayList Example:</strong></p>
 *
 * <p><strong>Purpose:</strong> The CopyOnWriteArrayList class is a thread-safe variant of ArrayList that allows concurrent reading and writing without locks by creating a new copy of the list on each write operation.</p>
 *
 * <p><strong>Usage:</strong> CopyOnWriteArrayList is useful when you need to allow multiple threads to read from a list simultaneously while occasionally modifying it without causing concurrent modification exceptions.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>Elements can be added, updated, or removed from the list without explicit synchronization.</li>
 *   <li>Concurrent readers can access the list without blocking or locking.</li>
 *   <li>Modifications are made on a cloned copy of the list, ensuring thread safety during writes.</li>
 *   <li>Iterators operate on the snapshot of the list created at the time of their creation.</li>
 * </ul>
 */
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // Add elements to the list
        list.add("Alice");
        list.add("Bob");
        list.add("Charlie");

        // Create a thread to modify the list
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add("David");
            list.remove("Alice");
        }).start();

        // Read from the list concurrently in the main thread
        for (int i = 0; i < 3; i++) {
            for (String element : list) {
                System.out.println("Read: " + element);
            }
            System.out.println("----");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
