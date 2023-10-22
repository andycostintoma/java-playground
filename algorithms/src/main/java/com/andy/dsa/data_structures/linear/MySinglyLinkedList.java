package com.andy.dsa.data_structures.linear;

import java.util.NoSuchElementException;

public class MySinglyLinkedList<T> {

    // Node class for storing data and reference to the next node
    public static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // Reference to the head of the list
    private Node<T> tail; // Reference to the tail of the list
    private int size;     // Number of elements in the list

    public MySinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add an element at the top of the list: O(1) time complexity
    public void prepend(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Add an element at the end of the list: O(1) time complexity
    public void append(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Add an element at a specific index: O(n) time complexity
    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            prepend(data);
        } else if (index == size) {
            append(data);
        } else {
            Node<T> newNode = new Node<>(data);
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    // Get the first element in the list: O(1) time complexity
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    // Get the last element in the list: O(1) time complexity
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    // Access an element by index: O(n) time complexity
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Search for an element and return its index: O(n) time complexity
    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Element not found
    }

    // Remove the element at the front of the list: O(1) time complexity
    private void removeFirst() {
        if (!isEmpty()) {
            head = head.next;
            size--;
            if (isEmpty()) {
                tail = null;
            }
        }
    }

    // Remove the element at the specified index: O(n) time complexity
    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            removeFirst();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }
    }

    // Remove an element by value: O(n) time complexity
    public void remove(T data) {
        if (isEmpty()) {
            return;
        }
        if (head.data.equals(data)) {
            removeFirst();
            return;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }

    // Get the size of the list: O(1) time complexity
    public int size() {
        return size;
    }

    // Check if the list is empty: O(1) time complexity
    private boolean isEmpty() {
        return size == 0;
    }

    // Print the elements of the list
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }


}
