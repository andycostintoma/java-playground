package com.andy.part4_streams.basics;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreamsExample {

    public static void main(String[] args) {
        // Primitive Streams
        System.out.println("Primitive Streams:");

        // Using autoboxing with Stream of Long
        Stream<Long> longStream = Stream.of(5L, 23L, 42L);
        System.out.println("Long Stream with Autoboxing:");
        longStream.forEach(System.out::println);

        // Using specialized IntStream
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        System.out.println("\nInt Stream:");
        intStream.forEach(System.out::println);

        // Using specialized LongStream
        LongStream longPrimitiveStream = LongStream.rangeClosed(1, 5);
        System.out.println("\nLong Primitive Stream:");
        longPrimitiveStream.forEach(System.out::println);

        // Using specialized DoubleStream
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5);
        System.out.println("\nDouble Stream:");
        doubleStream.forEach(System.out::println);

        // Specialized terminal operations on primitive Streams
        IntStream intNumbers = IntStream.of(1, 2, 3, 4, 5);
        int sum = intNumbers.sum();
        System.out.println("\nSum of IntStream: " + sum);

        OptionalInt min = IntStream.of(1, 2, 3, 4, 5).min();
        System.out.println("Min of IntStream: " + min.getAsInt());

        OptionalInt max = IntStream.of(1, 2, 3, 4, 5).max();
        System.out.println("Max of IntStream: " + max.getAsInt());

        OptionalDouble average = IntStream.of(1, 2, 3, 4, 5).average();
        System.out.println("Average of IntStream: " + average.orElse(0.0));
    }
}
