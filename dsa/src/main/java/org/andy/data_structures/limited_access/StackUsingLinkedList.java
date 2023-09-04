package org.andy.data_structures.limited_access;

import java.util.Deque;
import java.util.LinkedList;

public class StackUsingLinkedList {

    public static void main(String[] args) {
        Deque<String> stack = new LinkedList<>();

        // Push elements onto the stack (Time Complexity: O(1))
        stack.push("first");
        stack.push("second");
        stack.push("third");

        // Print the original stack
        System.out.println("Stack: " + stack);

        // Peek at the top element without removing it (Time Complexity: O(1))
        String topElement = stack.peek();
        System.out.println("Peek at the top element: " + topElement);

        // Pop and remove elements from the stack (Time Complexity: O(1))
        String poppedElement1 = stack.pop();
        String poppedElement2 = stack.pop();
        String poppedElement3 = stack.pop();

        System.out.println("Popped Elements: " + poppedElement1 + ", " + poppedElement2 + ", " + poppedElement3);

        // Try to pop from an empty stack (will throw EmptyStackException)
        // String poppedElement4 = stack.pop();
    }
}
