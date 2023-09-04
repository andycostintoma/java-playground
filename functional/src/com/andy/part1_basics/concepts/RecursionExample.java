package com.andy.part1_basics.concepts;

public class RecursionExample {
    public static int factorial(int n) {
        // Base case: factorial of 0 or 1 is 1
        if (n <= 1) {
            return 1;
        } else {
            // Recursive case: n! = n * (n-1)!
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        int number = 5;
        int result = factorial(number);
        System.out.println("Factorial of " + number + " is " + result);
    }
}
