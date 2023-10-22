package com.andy.dsa.data_structures.limited_access;

import java.util.LinkedList;
import java.util.Queue;

public class QueueUsingLinkedList {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        // Enqueue elements
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        // Peek at the front element
        System.out.println("Front element (peek): " + queue.peek()); // 10

        // Dequeue elements
        int dequeued1 = queue.poll(); // Removes and returns 10
        int dequeued2 = queue.poll(); // Removes and returns 20
        int dequeued3 = queue.poll(); // Removes and returns 30

        // Check if the queue is empty
        boolean isEmpty = queue.isEmpty(); // true

        System.out.println("Dequeuing elements: " + dequeued1 + ", " + dequeued2 + ", " + dequeued3);
        System.out.println("Is queue empty? " + isEmpty);
    }
}
