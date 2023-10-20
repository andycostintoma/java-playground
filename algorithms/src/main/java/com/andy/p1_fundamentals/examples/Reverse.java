package com.andy.p1_fundamentals.examples;




import edu.princeton.cs.algs4.p1_fundamentals.p3_bags_queues_stacks.Stack;
import edu.princeton.cs.algs4.utils.StdIn;
import edu.princeton.cs.algs4.utils.StdOut;

/**
 * Read a sequence of integers from standard input and print them
 * in reverse order.
 */

public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }
        for (int i : stack) {
            StdOut.println(i);
        }
    }
}
