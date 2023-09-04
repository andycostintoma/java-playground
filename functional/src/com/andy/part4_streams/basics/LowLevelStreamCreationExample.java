package com.andy.part4_streams.basics;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LowLevelStreamCreationExample {

    public static void main(String[] args) {
        // Low-Level Stream Creation

        // Creating a Stream from a Spliterator
        Spliterator<String> spliterator = Stream.of("apple", "banana", "orange")
                                                .spliterator();
        Stream<String> streamFromSpliterator = StreamSupport.stream(spliterator, false);
        streamFromSpliterator.forEach(System.out::println);

        // Creating a Stream from an Iterator
        Iterator<Integer> iterator = Stream.iterate(1, n -> n + 1)
                                           .limit(5)
                                           .iterator();
        Spliterator<Integer> iteratorSpliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        Stream<Integer> streamFromIterator = StreamSupport.stream(iteratorSpliterator, false);
        streamFromIterator.forEach(System.out::println);
    }
}
