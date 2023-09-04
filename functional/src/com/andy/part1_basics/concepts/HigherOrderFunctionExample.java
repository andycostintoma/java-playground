package com.andy.part1_basics.concepts;

import java.util.function.Function;

public class HigherOrderFunctionExample {
    public static void main(String[] args) {
        Function<Integer, Integer> increment = x -> x + 1;
        Function<Integer, Integer> square = x -> x * x;

        // Accepting a function as an argument
        int result1 = applyFunction(increment, 5); // Result: 6
        int result2 = applyFunction(square, 3);    // Result: 9

        System.out.println("Increment result: " + result1);
        System.out.println("Square result: " + result2);

        // Returning a function as a result
        Function<Integer, Function<Integer, Integer>> createMathOperation =
                operator -> value -> operator * value;

        Function<Integer, Integer> multiplyBy2 = createMathOperation.apply(2);
        Function<Integer, Integer> multiplyBy3 = createMathOperation.apply(3);

        int result3 = multiplyBy2.apply(5); // Result: 2 * 5 = 10
        int result4 = multiplyBy3.apply(4); // Result: 3 * 4 = 12

        System.out.println("Result 1: " + result3);
        System.out.println("Result 2: " + result4);
    }

    // Accepting a function as an argument
    public static int applyFunction(Function<Integer, Integer> func, int value) {
        return func.apply(value);
    }
}
