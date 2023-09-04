package com.andy.part4_streams.basics;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class FindingAndMatchingExample {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        firstEven.ifPresent(even -> System.out.println("First even number: " + even));

        Optional<Integer> anyEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findAny();
        anyEven.ifPresent(even -> System.out.println("Any even number: " + even));

        Predicate<Integer> isOdd = n -> n % 2 != 0;
        boolean allAreOdd = numbers.stream().allMatch(isOdd);
        System.out.println("Are all numbers odd? " + allAreOdd);

        boolean anyIsOdd = numbers.stream().anyMatch(isOdd);
        System.out.println("Is there any odd number? " + anyIsOdd);

        boolean noneIsEven = numbers.stream().noneMatch(n -> n % 2 == 0);
        System.out.println("Are there no even numbers? " + noneIsEven);
    }
}
