package com.andy.part1_basics.lambdas;


public class CustomFunctionalInterfaceExample {

    @FunctionalInterface
    interface MathOperation {
        double perform(double a, double b);
    }

    public static void main(String[] args) {
        // Using a custom functional interface to perform addition
        MathOperation addition = Double::sum;
        System.out.println("Addition: " + addition.perform(5, 3));

        // Using a custom functional interface to perform subtraction
        MathOperation subtraction = (a, b) -> a - b;
        System.out.println("Subtraction: " + subtraction.perform(10, 7));

        // Using a custom functional interface to perform multiplication
        MathOperation multiplication = (a, b) -> a * b;
        System.out.println("Multiplication: " + multiplication.perform(4, 6));
    }
}
