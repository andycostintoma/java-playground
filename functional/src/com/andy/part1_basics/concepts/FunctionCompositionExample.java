package com.andy.part1_basics.concepts;

import java.util.function.Function;

public class FunctionCompositionExample {
    public static void main(String[] args) {
        String inputString = "hello, world";

        // Define the functions
        Function<String, String> toUpperCaseFunction = String::toUpperCase;
        Function<String, String> appendExclamationFunction = s -> s + "!";

        // Compose the functions
        Function<String, String> composedFunction = appendExclamationFunction.compose(toUpperCaseFunction);

        // Apply the composed function to the input string
        String result = composedFunction.apply(inputString);

        // Print the result
        System.out.println("Original string: " + inputString);
        System.out.println("Processed string: " + result);
    }
}
