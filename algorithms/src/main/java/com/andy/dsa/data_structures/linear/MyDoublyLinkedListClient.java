package com.andy.dsa.data_structures.linear;

public class MyDoublyLinkedListClient {
    public static void main(String[] args) {
        MyDoublyLinkedList<Integer> list = new MyDoublyLinkedList<>();

        // Add elements to the list
        list.prepend(10);
        list.prepend(15);
        list.append(20);
        list.append(25);
        list.insert(2, 12);

        // Print the original list
        System.out.println("Doubly Linked List: ");
        list.printList();

        // Get the size of the list
        System.out.println("Size: " + list.size());

        // Access an element by index
        int elementAtIndex3 = list.get(3);
        System.out.println("Element at index 3: " + elementAtIndex3);

        // Getting a node
        MyDoublyLinkedList.Node<Integer> nodeAtIndex3 = list.getNodeAtIndex(3);

        // Adding a node before
        System.out.println("Adding value 42 before node at index 3...");
        list.addBefore(nodeAtIndex3, 42);
        list.printList();

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
        System.out.println("Doubly Linked List after removing " + elementToRemove + ": ");
        list.printList();

        // Remove the element at a specific index
        int indexToRemove = 2;
        list.removeAtIndex(indexToRemove);
        System.out.println("Doubly Linked List after removing element at index " + indexToRemove + ": ");
        list.printList();

        System.out.println("Printing the list in reverse");
        list.printReverse();


    }
}
