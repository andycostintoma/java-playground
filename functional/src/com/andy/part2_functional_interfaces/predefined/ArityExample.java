package com.andy.part2_functional_interfaces.predefined;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ArityExample {
    public static void main(String[] args) {
        // UnaryOperator example
        UnaryOperator<Integer> incrementByOne = num -> num + 1;
        System.out.println("Increment 5 by one: " + incrementByOne.apply(5));

        // BinaryOperator example
        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println("Sum of 3 and 7: " + sum.apply(3, 7));

        // Using Function for the same purpose as BinaryOperator
        Function<Integer, Function<Integer, Integer>> sumFunc = num1 -> num2 -> num1 + num2;
        System.out.println("Sum of 4 and 6 using Function: " + sumFunc.apply(4).apply(6));
    }
}
