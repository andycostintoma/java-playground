package com.andy.part4_streams.basics;

import java.util.stream.Stream;

public class OperationsCostExample {

    public static void main(String[] args) {

        Stream.of("pineapple", "oranges", "apple", "pear", "banana")
                .map(String::toUpperCase)  // Operation 1
                .sorted()  // Operation 2
                .filter(s -> s.startsWith("A"))  // Operation 3
                .forEach(System.out::println);  // Terminal operation

        // Optimized pipeline
        Stream.of("pineapple", "oranges", "apple", "pear", "banana")
                .filter(s -> s.startsWith("a"))  // Operation 1
                .map(String::toUpperCase)  // Operation 2
                .sorted()  // Operation 3
                .forEach(System.out::println);  // Terminal operation


        // Count short-circuiting -> peek and map are ignored because they do not affect the size
        long count = Stream.of("apple", "orange", "banana", "melon")
                .peek(str -> System.out.println("peek 1: " + str))
                .map(str -> {
                    System.out.println("map: " + str);
                    return str.toUpperCase();
                })
                .peek(str -> System.out.println("peek 2: " + str))
                .count();

        System.out.println("Count: " + count);
    }
}
