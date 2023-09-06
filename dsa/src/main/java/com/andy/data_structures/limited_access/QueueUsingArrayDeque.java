package com.andy.data_structures.limited_access;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueUsingArrayDeque {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();

        // Enqueue elements
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");

        // Peek at the front element
        System.out.println("Front element (peek): " + queue.peek()); // first

        // Dequeue elements
        String dequeued1 = queue.poll(); // Removes and returns "first"
        String dequeued2 = queue.poll(); // Removes and returns "second"
        String dequeued3 = queue.poll(); // Removes and returns "third"

        // Check if the queue is empty
        boolean isEmpty = queue.isEmpty(); // true

        System.out.println("Dequeuing elements: " + dequeued1 + ", " + dequeued2 + ", " + dequeued3);
        System.out.println("Is queue empty? " + isEmpty);
    }
}
