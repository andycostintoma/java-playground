package com.andy.part1_basics.concepts;

public class ReferentialTransparencyExample {
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int result1 = add(3, 5); // Result: 8

        int x = 3, y = 5;
        int result2 = add(x, y); // Result: 8

        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);
    }
}
