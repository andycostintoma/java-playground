package com.andy.part6_lazy_evaluation;

import java.util.function.Supplier;

/**
 * This class demonstrates the concepts of strict and lazy evaluation using Java Suppliers.
 * Strict evaluation calculates results immediately, while lazy evaluation delays the calculation until needed.
 */

public class LazyEvaluationUsingSuppliers {

    public static void main(String[] args) {

        // Strict Evaluation
        try {
            int strictResult = addStrict(5, divideByZero());
            System.out.println("Strict Result: " + strictResult);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        // Lazy Evaluation
        Supplier<Integer> lazyResult = () -> addLazy(() -> 5, () -> divideByZero());
        System.out.println("Lazy Result: " + lazyResult.get());
    }

    // Strict Evaluation
    static int addStrict(int x, int y) {
        return x + x;
    }

    static int divideByZero() {
        System.out.println("Divide by zero called!");
        return 1 / 0;
    }

    // Lazy Evaluation
    static int addLazy(Supplier<Integer> x, Supplier<Integer> y) {
        int actualX = x.get();
        return actualX + actualX;
    }
}
