package com.andy.part4_streams.basics;

import java.util.stream.IntStream;

public class IterativeStreamsExample {

    public static void main(String[] args) {

        // Using iterate to create an IntStream
        System.out.println("Using iterate to create an IntStream:");
        IntStream.iterate(1,
                        idx -> idx < 5,
                        idx -> idx + 1)
                .forEachOrdered(System.out::println);

        // Using range to create an IntStream
        System.out.println("Using range to create an IntStream:");
        IntStream.range(1, 5)
                .forEach(System.out::println);

        // Using rangeClosed to create an IntStream (inclusive)
        System.out.println("Using rangeClosed to create an IntStream (inclusive):");
        IntStream.rangeClosed(1, 5)
                .forEach(System.out::println);
    }
}
