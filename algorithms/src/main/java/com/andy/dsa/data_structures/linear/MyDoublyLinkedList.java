package com.andy.dsa.data_structures.linear;

import java.util.NoSuchElementException;

public class MyDoublyLinkedList<T> {

    // Node class for storing data and references to the previous and next nodes
    public static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

    }

    private Node<T> head; // Reference to the head of the list
    private Node<T> tail; // Reference to the tail of the list
    private int size;     // Number of elements in the list

    public MyDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add an element at the top of the list: O(1) time complexity - same as Singly
    public void prepend(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Add an element at the end of the list: O(1) time complexity - same as Singly
    public void append(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    // Add an element at a specific index: O(n) time complexity - reverse traversal when beneficial
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
            Node<T> current;
            if (index <= size / 2) {
                current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
            } else {
                current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }

    // Add an element before a specified node: O(1) time complexity
    public void addBefore(Node<T> specifiedNode, T data) {
        if (specifiedNode == null) {
            throw new IllegalArgumentException("Specified node cannot be null");
        }

        Node<T> newNode = new Node<>(data);

        // Update next and prev references for the new node
        newNode.next = specifiedNode;
        newNode.prev = specifiedNode.prev;

        // Update next reference of the previous node of specifiedNode
        if (specifiedNode.prev != null) {
            specifiedNode.prev.next = newNode;
        } else {
            // If specifiedNode is the head, update the head reference
            head = newNode;
        }

        // Update prev reference of specifiedNode
        specifiedNode.prev = newNode;

        size++;
    }

    // Get the first element in the list: O(1) time complexity - same as Singly
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    // Get the last element in the list: O(1) time complexity - same as Singly
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    // Access an element by index: O(n) time complexity - reverse traversal when beneficial
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    // Get the node at a specific index: O(n) time complexity - reverse traversal when beneficial
    public Node<T> getNodeAtIndex(int index) {
        Node<T> current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // Search for an element and return its index: O(n) time complexity - same as Singly
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

    // Remove the element at the front of the list: O(1) time complexity - same as Singly
    private void removeFirst() {
        if (!isEmpty()) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        }
    }

    // Remove the element at the end of the list: O(1) time complexity - better than Singly (where it's O(n))
    private void removeLast() {
        if (!isEmpty()) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
        }
    }

    // Remove the element at the specified index: O(n) time complexity - reverse traversal when beneficial
    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<T> current;
            if (index <= size / 2) {
                current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
            } else {
                current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
            }
            current.next = current.next.next;
            current.next.prev = current;
            size--;
        }
    }

    // Remove an element by value: O(n) time complexity - same as Singly
    public void remove(T data) {
        if (isEmpty()) {
            return;
        }
        if (head.data.equals(data)) {
            removeFirst();
        } else if (tail.data.equals(data)) {
            removeLast();
        } else {
            Node<T> current = head;
            while (current != null && !current.data.equals(data)) {
                current = current.next;
            }
            if (current != null) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
            }
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
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Print the elements of the list in reverse order
    public void printReverse() {
        Node<T> current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }


}
