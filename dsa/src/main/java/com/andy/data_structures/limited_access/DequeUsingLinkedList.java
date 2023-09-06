package com.andy.data_structures.limited_access;

import java.util.Deque;
import java.util.LinkedList;

public class DequeUsingLinkedList {
    public static void main(String[] args) {
        // Create a new LinkedList-based Deque
        Deque<Integer> deque = new LinkedList<>();

        // Add elements to the front and back
        // Adding to front: O(1) amortized time complexity
        // Adding to back: O(1) time complexity
        deque.addFirst(1);  // O(1)
        deque.addLast(2);   // O(1)
        deque.addFirst(3);  // O(1)

        // Access elements from the front and back
        // Accessing front element: O(1) time complexity
        // Accessing back element: O(1) time complexity
        int firstElement = deque.getFirst(); // Retrieves 3  // O(1)
        int lastElement = deque.getLast();   // Retrieves 2  // O(1)

        System.out.println("First Element: " + firstElement);
        System.out.println("Last Element: " + lastElement);

        // Remove elements from the front and back
        // Removing from front: O(1) time complexity
        // Removing from back: O(1) time complexity
        deque.removeFirst(); // Removes 3  // O(1)
        deque.removeLast();  // Removes 2  // O(1)

        System.out.println("Deque after removal: " + deque);
    }
}
