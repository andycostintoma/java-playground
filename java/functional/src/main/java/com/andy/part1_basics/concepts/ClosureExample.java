package com.andy.part1_basics.concepts;

import java.util.function.Supplier;

public class ClosureExample {
    public static void main(String[] args) {
        int outerVar = 10;

        Supplier<Integer> closure = () -> {
            // The lambda captures and "closes over" outerVar
            return outerVar + 5;
        };

        int result = closure.get();
        System.out.println("Result: " + result);
    }
}
