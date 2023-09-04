package org.andy.data_structures.linear;

import java.util.NoSuchElementException;

public class SinglyLinkedListExplained {

    private static class SinglyLinkedList<T> {

        // Node class for storing data and reference to the next node
        private static class Node<T> {
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

        private SinglyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        // Add an element at the top of the list: O(1) time complexity
        private void prepend(T data) {
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
        private void append(T data) {
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
        private void insert(int index, T data) {
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
        private T getFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("List is empty");
            }
            return head.data;
        }

        // Get the last element in the list: O(1) time complexity
        private T getLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("List is empty");
            }
            return tail.data;
        }

        // Access an element by index: O(n) time complexity
        private T get(int index) {
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
        private int indexOf(T data) {
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
        private void removeAtIndex(int index) {
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
        private void remove(T data) {
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
        private int size() {
            return size;
        }

        // Check if the list is empty: O(1) time complexity
        private boolean isEmpty() {
            return size == 0;
        }

        // Print the elements of the list
        private void printList() {
            Node<T> current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        // Add elements to the list
        list.prepend(10);
        list.prepend(15);
        list.append(20);
        list.append(25);
        list.insert(2, 12);

        // Print the original list
        System.out.println("Linked List: ");
        list.printList();

        // Get the size of the list
        System.out.println("Size: " + list.size());

        // Access an element by index
        int elementAtIndex3 = list.get(3);
        System.out.println("Element at index 3: " + elementAtIndex3);

        // Get the first and last elements
        int firstElement = list.getFirst();
        int lastElement = list.getLast();
        System.out.println("First Element: " + firstElement);
        System.out.println("Last Element: " + lastElement);

        // Search for an element and return its index
        int indexToSearch = list.indexOf(20);
        System.out.println("Index of element 20: " + indexToSearch);

        // Remove an element by value
        int elementToRemove = 20;
        list.remove(elementToRemove);
        System.out.println("Linked List after removing " + elementToRemove + ": ");
        list.printList();

        // Remove the element at a specific index
        int indexToRemove = 2;
        list.removeAtIndex(indexToRemove);
        System.out.println("Linked List after removing element at index " + indexToRemove + ": ");
        list.printList();
    }
}