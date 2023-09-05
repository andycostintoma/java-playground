package com.andy.part1_basics.concepts.lazy_evaluation;

import java.util.function.Supplier;

public class LazyEvaluationExample {
    public static void main(String[] args) {
        // Define the time-consuming operation
        Supplier<Integer> timeConsumingOperation = () -> {
            System.out.println("Performing time-consuming operation...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5 + 3; // Simulating a complex computation
        };

        // Use lazy evaluation
        Supplier<Integer> lazyResult = () -> {
            System.out.println("Lazy evaluation requested");
            return timeConsumingOperation.get();
        };

        // The time-consuming operation hasn't been executed yet
        System.out.println("Before lazy evaluation");

        // Request the lazy evaluation
        int result = lazyResult.get();

        // The time-consuming operation is executed only at this point
        System.out.println("After lazy evaluation: Result = " + result);
    }
}
