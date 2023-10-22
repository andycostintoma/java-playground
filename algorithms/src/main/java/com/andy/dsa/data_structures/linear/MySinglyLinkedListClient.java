package com.andy.dsa.data_structures.linear;

public class MySinglyLinkedListClient {
    public static void main(String[] args) {
        MySinglyLinkedList<Integer> list = new MySinglyLinkedList<>();

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
