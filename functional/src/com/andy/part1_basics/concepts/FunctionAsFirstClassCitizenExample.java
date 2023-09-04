package com.andy.part1_basics.concepts;

import java.util.function.Function;

public class FunctionAsFirstClassCitizenExample {
    public static void main(String[] args) {
        // Passing functions as parameters
        int result1 = applyOperation(5, x -> x * x);
        int result2 = applyOperation(10, x -> x + 5);

        System.out.println("Result of x * x: " + result1);
        System.out.println("Result of x + 5: " + result2);

        // Returning a function from another function
        Function<Integer, Integer> operation = getOperation("multiply");
        int result3 = operation.apply(6);

        System.out.println("Result of 6 * 6: " + result3);
    }

    // Function as parameter
    public static int applyOperation(int value, Function<Integer, Integer> operation) {
        return operation.apply(value);
    }

    // Function as return type
    public static Function<Integer, Integer> getOperation(String operationType) {
        if ("multiply".equals(operationType)) {
            return x -> x * x;
        } else {
            return x -> x;
        }
    }
}
