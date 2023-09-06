package com.andy.data_structures.limited_access;

import java.util.NoSuchElementException;

public class DequeUsingLinkedListExplained {

    private static class Deque<T> {

        private static class Node<T> {
            T data;
            Node<T> next;
            Node<T> prev;

            Node(T data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }

        private Node<T> front; // Reference to the front of the deque
        private Node<T> back;  // Reference to the back of the deque
        private int size;      // Number of elements in the deque

        public Deque() {
            front = null;
            back = null;
            size = 0;
        }

        // Insert an element at the front of the deque: O(1) time complexity
        private void insertFront(T data) {
            Node<T> newNode = new Node<>(data);
            if (isEmpty()) {
                front = newNode;
                back = newNode;
            } else {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size++;
        }

        // Insert an element at the back of the deque: O(1) time complexity
        private void insertBack(T data) {
            Node<T> newNode = new Node<>(data);
            if (isEmpty()) {
                front = newNode;
                back = newNode;
            } else {
                newNode.prev = back;
                back.next = newNode;
                back = newNode;
            }
            size++;
        }

        // Remove the element from the front of the deque: O(1) time complexity
        private void removeFront() {
            if (!isEmpty()) {
                front = front.next;
                if (front != null) {
                    front.prev = null;
                } else {
                    back = null; // Deque is empty after removal
                }
                size--;
            }
        }

        // Remove the element from the back of the deque: O(1) time complexity
        private void removeBack() {
            if (!isEmpty()) {
                back = back.prev;
                if (back != null) {
                    back.next = null;
                } else {
                    front = null; // Deque is empty after removal
                }
                size--;
            }
        }

        // Get the front element of the deque: O(1) time complexity
        private T getFront() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            return front.data;
        }

        // Get the back element of the deque: O(1) time complexity
        private T getBack() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            return back.data;
        }

        // Get the size of the deque: O(1) time complexity
        private int size() {
            return size;
        }

        // Check if the deque is empty: O(1) time complexity
        private boolean isEmpty() {
            return size == 0;
        }

        // Print the elements of the deque
        private void printDeque() {
            Node<T> current = front;
            while (current != null) {
                System.out.print(current.data + " <-> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // Insert elements at the front and back of the deque
        deque.insertFront(10);
        deque.insertBack(20);
        deque.insertFront(5);
        deque.insertBack(30);

        // Print the deque
        System.out.println("Deque: ");
        deque.printDeque();

        // Get the front and back elements
        int frontElement = deque.getFront();
        int backElement = deque.getBack();
        System.out.println("Front Element: " + frontElement);
        System.out.println("Back Element: " + backElement);

        // Remove elements from the front and back of the deque
        deque.removeFront();
        deque.removeBack();

        // Print the deque after removal
        System.out.println("Deque after removal: ");
        deque.printDeque();
    }
}
