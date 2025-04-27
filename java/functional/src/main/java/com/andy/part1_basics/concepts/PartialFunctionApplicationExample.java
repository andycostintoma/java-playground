package com.andy.part1_basics.concepts;

import java.util.function.Function;

public class PartialFunctionApplicationExample {

    public static void main(String[] args) {
        // Define the original function
        Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;

        // Partially apply the original function to create a more specific function
        Function<Integer, Integer> add3 = add.apply(3);

        // Use the partially applied function
        int result = add3.apply(5);
        System.out.println("Result: " + result); // Output: Result: 8
    }
}
