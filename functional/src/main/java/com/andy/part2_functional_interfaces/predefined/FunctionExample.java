package com.andy.part2_functional_interfaces.predefined;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // Define a Function to convert a String to its length as Integer
        Function<String, Integer> stringLengthFunction = String::length;

        // Apply the Function to a String
        String input = "Hello, Function!";
        Integer result = stringLengthFunction.apply(input);

        // Print the result
        System.out.println("Length of the input string: " + result);
    }
}
