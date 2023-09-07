package com.andy.part1_basics.concepts;

import java.util.function.Function;

public class CurryingExample {
    public static void main(String[] args) {
        // Create a curried function that adds two numbers
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;

        // Apply the curried function
        int result = curriedAdd.apply(5).apply(3);
        System.out.println("Result: " + result);  // Output: 8
    }
}
