package com.andy.part1_basics.concepts;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MemoizationExample {

    public static void main(String[] args) {
        // Create a memoization cache
        Map<Integer, Integer> cache = new HashMap<>();

        // Calculate the square of a number using memoization
        int result1 = memoize(5, () -> calculateSquare(5), cache);
        int result2 = memoize(8, () -> calculateSquare(8), cache);

        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);

        System.out.println("Using the cache...");
        int result3 = memoize(5, () -> calculateSquare(5), cache);

        System.out.println("Result 3: " + result3);
    }

    // A pure function to calculate the square of a number
    static int calculateSquare(int x) {
        System.out.println("Calculating square of " + x);
        return x * x;
    }

    // Memoize a function using a cache
    static <T> T memoize(Integer key, Supplier<T> fn, Map<Integer, T> cache) {
        return cache.computeIfAbsent(key, k -> fn.get());
    }
}
