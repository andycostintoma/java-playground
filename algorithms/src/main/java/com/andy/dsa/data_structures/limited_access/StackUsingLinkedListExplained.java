package com.andy.dsa.data_structures.limited_access;

import java.util.EmptyStackException;

public class StackUsingLinkedListExplained {

    private static class Stack<T> {

        // Node class for storing data and reference to the next node
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node<T> top; // Reference to the top of the stack
        private int size;    // Number of elements in the stack

        private Stack() {
            top = null;
            size = 0;
        }

        // Push an element onto the stack: O(1) time complexity
        private void push(T data) {
            Node<T> newNode = new Node<>(data);
            newNode.next = top;
            top = newNode;
            size++;
        }

        // Pop and remove the top element from the stack: O(1) time complexity
        private T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T poppedData = top.data;
            top = top.next;
            size--;
            return poppedData;
        }

        // Peek and return the top element without removing it: O(1) time complexity
        private T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return top.data;
        }

        // Check if the stack is empty: O(1) time complexity
        private boolean isEmpty() {
            return size == 0;
        }

        // Get the size of the stack: O(1) time complexity
        private int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack size: " + stack.size());
        System.out.println("Element on top: " + stack.peek());

        // Print the original stack
        System.out.println("Stack: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " -> ");
        }
        System.out.println("null");

        // Check if the stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty());

        // Try to pop from an empty stack (will throw EmptyStackException)
        // System.out.println("Pop from an empty stack: " + stack.pop());
    }
}
