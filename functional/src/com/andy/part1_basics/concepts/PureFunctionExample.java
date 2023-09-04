package com.andy.part1_basics.concepts;

import java.util.function.Function;

public class PureFunctionExample {
    public static void main(String[] args) {

    /*
        This function is pure because it follows the principles of a pure function:
        1. It has a consistent output for the same input.
        2. It doesn't modify external state.
        3. Its result depends solely on the input parameter.
    */
        Function<Integer, Integer> squareFunction = x -> x * x;

        int number = 5;
        int squaredNumber = squareFunction.apply(number);

        System.out.println("Square of " + number + " is: " + squaredNumber);
    }
}
