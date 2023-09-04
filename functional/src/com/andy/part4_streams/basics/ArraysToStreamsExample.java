package com.andy.part4_streams.basics;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class ArraysToStreamsExample {

    public static void main(String[] args) {

        // Object-Type Arrays
        String[] fruits = new String[] { "Banana", "Melon", "Orange" };

        // Creating Stream from an array
        System.out.println("Creating Stream from an array:");
        Arrays.stream(fruits)
              .filter(fruit -> fruit.contains("a"))
              .forEach(System.out::println);

        // Creating an array from a Stream
        System.out.println("Creating an array from a Stream:");
        String[] result = Arrays.stream(fruits)
                                .filter(fruit -> fruit.contains("a"))
                                .toArray(String[]::new);
        System.out.println(Arrays.toString(result));

        // Primitive Arrays
        int[] fibonacci = new int[] { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 };

        // Creating IntStream from an int array
        System.out.println("Creating IntStream from an int array:");
        IntStream intStream = Arrays.stream(fibonacci);
        intStream.forEach(System.out::println);

        // Creating an int array from an IntStream
        System.out.println("Creating an int array from an IntStream:");
        int[] evenNumbers = Arrays.stream(fibonacci)
                                  .filter(value -> value % 2 == 0)
                                  .toArray();
        System.out.println(Arrays.toString(evenNumbers));

        // Primitive Arrays for DoubleStream and LongStream are similar
        double[] prices = new double[] { 25.5, 49.99, 30.0 };

        // Creating DoubleStream from a double array
        System.out.println("Creating DoubleStream from a double array:");
        DoubleStream doubleStream = Arrays.stream(prices);
        doubleStream.forEach(System.out::println);
    }
}
