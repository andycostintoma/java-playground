package com.andy.part4_streams.basics;

import java.util.stream.Stream;

public class ModifyingStreamBehaviorExample {

    public static void main(String[] args) {
        // Modifying Stream Behavior

        System.out.println("Sequential Stream:");
        Stream<String> sequentialStream = Stream.of("apple", "orange", "banana", "melon");
        sequentialStream.forEach(System.out::println);  // Sequential processing

        System.out.println("\nParallel Stream:");
        Stream<String> parallelStream = Stream.of("apple", "orange", "banana", "melon").parallel();
        parallelStream.forEach(System.out::println);  // Parallel processing

        System.out.println("\nUnordered Stream:");
        Stream<String> unorderedStream = Stream.of("apple", "orange", "banana", "melon").unordered();
        unorderedStream.forEachOrdered(System.out::println);  // Unordered processing

        System.out.println("\nStream with Close Handler:");
        Stream<String> streamWithCloseHandler = Stream.of("apple", "orange", "banana", "melon")
                .onClose(() -> System.out.println("\nStream is closing..."));
        streamWithCloseHandler.forEach(System.out::println);
        streamWithCloseHandler.close();  // Calling close handler
    }
}
