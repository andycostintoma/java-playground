package org.andy.data_structures.linear;

import java.util.LinkedList;


public class LinkedListExample {

    public static void main(String[] args) {

        // Uses doubly linked list under the hood
        LinkedList<Integer> list = new LinkedList<>();

        // Add elements to the list
        list.addFirst(10); // Add to the front: O(1) time complexity
        list.addFirst(15);
        list.addLast(20); // Add to the end: O(1) time complexity
        list.addLast(25);
        list.add(2, 12); // Add at a specific index: O(n) time complexity

        // Print the original list
        System.out.println("Linked List: ");
        System.out.println(list);

        // Get the size of the list
        System.out.println("Size: " + list.size());

        // Access an element by index: O(n) time complexity
        int elementAtIndex3 = list.get(3);
        System.out.println("Element at index 3: " + elementAtIndex3);

        // Get the first and last elements
        int firstElement = list.getFirst(); // Get first element: O(1) time complexity
        int lastElement = list.getLast(); // Get last element: O(1) time complexity
        System.out.println("First Element: " + firstElement);
        System.out.println("Last Element: " + lastElement);

        // Search for an element and return its index
        int indexToSearch = list.indexOf(20); // Search by value: O(n) time complexity
        System.out.println("Index of element 20: " + indexToSearch);

        // Remove an element by value
        int elementToRemove = 20;
        list.remove(Integer.valueOf(elementToRemove)); // Remove by value: O(n) time complexity
        System.out.println("Linked List after removing " + elementToRemove + ": ");
        System.out.println(list);

        // Remove the element at a specific index
        int indexToRemove = 2;
        list.remove(indexToRemove); // Remove by index: O(n) time complexity
        System.out.println("Linked List after removing element at index " + indexToRemove + ": ");
        System.out.println(list);
    }
}
