package com.andy.part4_streams.basics;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class RandomNumbersExample {

    public static void main(String[] args) {
        // Random Numbers with Streams
        System.out.println("Random Numbers with Streams:");

        // Using java.util.Random
        System.out.println("Using java.util.Random:");
        Random random = new Random();
        IntStream randomIntStream = random.ints(5, 1, 11);
        randomIntStream.forEach(System.out::println);

        // Using java.util.concurrent.ThreadLocalRandom
        System.out.println("Using java.util.concurrent.ThreadLocalRandom:");
        IntStream threadLocalRandomIntStream = ThreadLocalRandom.current().ints(5, 1, 11);
        threadLocalRandomIntStream.forEach(System.out::println);

        // Using java.util.SplittableRandom
        System.out.println("Using java.util.SplittableRandom:");
        IntStream splittableRandomIntStream = new SplittableRandom().ints(5, 1, 11);
        splittableRandomIntStream.forEach(System.out::println);

        // Using java.util.Random to generate DoubleStream
        System.out.println("Using java.util.Random to generate DoubleStream:");
        DoubleStream randomDoubleStream = random.doubles(5, 0.0, 1.0);
        randomDoubleStream.forEach(System.out::println);
    }
}
