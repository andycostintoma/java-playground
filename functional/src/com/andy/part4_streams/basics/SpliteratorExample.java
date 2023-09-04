package com.andy.part4_streams.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorExample {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }

        // Create a Spliterator from the list
        Spliterator<Integer> spliterator = numbers.spliterator();

        // Create a parallel stream using the Spliterator
        Stream<Integer> parallelStream = StreamSupport.stream(spliterator, true);

        // Perform an action on each element in parallel
        parallelStream.forEach(e -> System.out.println(Thread.currentThread().getName() + ": " + e));
    }
}
