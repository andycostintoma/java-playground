package com.andy.part2_functional_interfaces;

import java.util.function.Function;

public class FunctionalCompositionExample {
    public static void main(String[] args) {
        // Define two functions for composition
        Function<String, String> removeLowerCaseA = str -> str.replace("a", "");
        Function<String, String> upperCase = String::toUpperCase;

        // Input string
        String input = "abcd";

        // Compose functions using andThen
        String resultAndThen = removeLowerCaseA.andThen(upperCase).apply(input);
        System.out.println("Result using andThen composition: " + resultAndThen);

        // Compose functions using compose
        String resultCompose = upperCase.compose(removeLowerCaseA).apply(input);
        System.out.println("Result using compose composition: " + resultCompose);
    }
}
