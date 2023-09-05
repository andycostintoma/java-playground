package com.andy.part1_basics.concepts.recursion;

public class HeadRecursionExample {

    public static void main(String[] args) {
        long result = factorial(4L);
        System.out.println("Factorial of 4 is: " + result);
    }

    private static long factorial(long n) {
        if (n == 1L) {
            return 1L; // Base condition: factorial of 1 is 1
        }

        long nextN = n - 1L;

        // Recursive call comes before other calculations
        return n * factorial(nextN);
    }
}
