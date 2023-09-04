package com.andy.part4_streams.basics;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteStreamsExample {

    public static void main(String[] args) {

        Stream.generate(UUID::randomUUID)
                .limit(5)
                .forEach(System.out::println);

        IntStream.generate(new AtomicInteger()::incrementAndGet)
                .limit(1_000L)
                .max()
                .ifPresent(System.out::println);
    }


}
