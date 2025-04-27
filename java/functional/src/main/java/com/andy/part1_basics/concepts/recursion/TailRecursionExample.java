package com.andy.part1_basics.concepts.recursion;

public class TailRecursionExample {

    public static void main(String[] args) {
        long result = factorial(4L, 1L);
        System.out.println("Factorial of 4 is: " + result);
    }

    private static long factorial(long n, long accumulator) {
        if (n == 1L) {
            return accumulator; // Base condition: return the accumulated result
        }

        long nextN = n - 1L;
        long nextAccumulator = n * accumulator;

        // Tail-recursive call
        return factorial(nextN, nextAccumulator);
    }
}
