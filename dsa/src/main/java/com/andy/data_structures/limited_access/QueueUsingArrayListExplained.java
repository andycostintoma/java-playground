package com.andy.data_structures.limited_access;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class QueueUsingArrayListExplained {

    private static class Queue<T> {

        private final ArrayList<T> list;

        private Queue() {
            list = new ArrayList<>();
        }

        // Enqueue (insert) an element at the rear of the queue: O(1) time complexity
        private void enqueue(T data) {
            list.add(data);
        }

        // Dequeue (remove) and return the front element from the queue: O(n) time complexity
        private T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return list.remove(0);
        }

        // Peek and return the front element without removing it: O(1) time complexity
        private T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return list.get(0);
        }

        private boolean isEmpty() {
            return list.isEmpty();
        }

        private int size() {
            return list.size();
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        // Enqueue elements into the queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Peek
        System.out.println("First element: " + queue.peek());

        // Dequeue
        System.out.println("First element removed: " + queue.dequeue());

        // Check if the queue is empty
        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}
