package org.andy.data_structures.limited_access;

import java.util.NoSuchElementException;

public class QueueUsingLinkedListExplained {

    private static class Queue<T> {

        // Node class for storing data and reference to the next node
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node<T> front; // Reference to the front of the queue
        private Node<T> rear;  // Reference to the rear of the queue
        private int size;      // Number of elements in the queue

        private Queue() {
            front = null;
            rear = null;
            size = 0;
        }

        // Enqueue (insert) an element at the rear of the queue: O(1) time complexity
        private void enqueue(T data) {
            Node<T> newNode = new Node<>(data);
            if (isEmpty()) {
                front = newNode;
            } else {
                rear.next = newNode;
            }
            rear = newNode;
            size++;
        }

        // Dequeue (remove) and return the front element from the queue: O(1) time complexity
        private T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            T dequeuedData = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
            return dequeuedData;
        }

        // Peek and return the front element without removing it: O(1) time complexity
        private T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return front.data;
        }

        private boolean isEmpty() {
            return size == 0;
        }

        private int size() {
            return size;
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
