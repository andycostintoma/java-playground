package com.andy.multithreading.p5_concurrent_collections;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * <p><strong>PriorityBlockingQueue Example:</strong></p>
 *
 * <p><strong>Purpose:</strong> PriorityBlockingQueue is an implementation of a blocking priority queue. It allows you to store elements with a specified order based on their priority.</p>
 *
 * <p><strong>Usage:</strong> PriorityBlockingQueue is useful when you need to process elements in a specific order, such as a priority order. Elements are automatically ordered based on their natural order or using a custom comparator.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>PriorityBlockingQueue automatically orders elements based on their natural order (if they implement Comparable) or using a provided comparator.</li>
 *   <li>It is a blocking queue, meaning that it provides thread-safe operations and blocking behavior for both putting and taking elements.</li>
 *   <li>When you add elements, they are immediately ordered based on their priority.</li>
 *   <li>Higher-priority elements are dequeued before lower-priority elements.</li>
 * </ul>
 */


public class PriorityBlockingQueueExample {

    record Person(String name, int age) implements Comparable<Person> {

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return name + " (Age: " + age + ")";
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<Person> priorityQueue = new PriorityBlockingQueue<>();

        // Producer thread
        new Thread(() -> {
            priorityQueue.put(new Person("Alice", 25));
            priorityQueue.put(new Person("Bob", 30));
            priorityQueue.put(new Person("Charlie", 20));
            priorityQueue.put(new Person("David", 22));
            priorityQueue.put(new Person("Eve", 18));
        }).start();

        // Consumer thread
        new Thread(() -> {
            while (true) {
                try {
                    Person person = priorityQueue.take();
                    System.out.println("Processing: " + person);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
}
