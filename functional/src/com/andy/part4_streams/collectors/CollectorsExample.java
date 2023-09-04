package com.andy.part4_streams.collectors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {

    public static void main(String[] args) {
        // Collecting Stream elements to a List
        List<String> fruitList = Stream.of("apple", "orange", "banana", "peach")
                .collect(Collectors.toList());
        System.out.println("Fruit List: " + fruitList);

        // Collecting Stream elements to a Set
        Set<String> fruitSet = Stream.of("apple", "orange", "banana", "peach")
                .collect(Collectors.toSet());
        System.out.println("Fruit Set: " + fruitSet);

        // Collecting Stream elements to a Map based on fruit length
        Map<Integer, String> fruitLengthMap = Stream.of("apple", "orange")
                .collect(Collectors.toMap(
                        String::length,
                        fruit -> fruit
                ));
        System.out.println("Fruit Length Map: " + fruitLengthMap);

        // Grouping Stream elements by their length
        Map<Integer, List<String>> fruitGroupedByLength = Stream.of("apple", "orange", "banana", "peach")
                .collect(Collectors.groupingBy(
                        String::length
                ));
        System.out.println("Fruit Grouped by Length: " + fruitGroupedByLength);

        // Partitioning Stream elements by even and odd lengths
        Map<Boolean, List<String>> fruitPartitionedByEvenLength = Stream.of("apple", "orange", "banana", "peach")
                .collect(Collectors.partitioningBy(
                        fruit -> fruit.length() % 2 == 0
                ));
        System.out.println("Fruit Partitioned by Even Length: " + fruitPartitionedByEvenLength);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // Calculating the average of numbers
        double average = numbers.stream()
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Average: " + average);

        // Calculating the sum of numbers
        int sum = numbers.stream()
                .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum: " + sum);

        // Collecting summary statistics of numbers
        IntSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Statistics: " + stats);

        // Counting the number of elements
        long count = numbers.stream()
                .collect(Collectors.counting());
        System.out.println("Count: " + count);

        // Finding the minimum and maximum numbers
        int min = numbers.stream()
                .collect(Collectors.minBy(Integer::compare))
                .orElse(0);
        int max = numbers.stream()
                .collect(Collectors.maxBy(Integer::compare))
                .orElse(0);
        System.out.println("Min: " + min + ", Max: " + max);

        // Joining Stream elements into a single String
        String fruitJoined = Stream.of("apple", "orange", "banana", "peach")
                .collect(Collectors.joining(", "));
        System.out.println("Joined: " + fruitJoined);

        // Advanced Use Case: reducing
        Optional<Integer> sumOptional = numbers.stream()
                .collect(Collectors.reducing(Integer::sum));
        sumOptional.ifPresent(s -> System.out.println("Reducing Sum: " + s));

        // Advanced Use Case: collectingAndThen
        String maxNumberAsString = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.collectingAndThen(
                        Collectors.maxBy(String::compareTo),
                        maxOpt -> maxOpt.orElse("N/A")
                ));
        System.out.println("Max Number as String: " + maxNumberAsString);

        // Advanced Use Case: mapping
        Map<Integer, List<String>> numberToStrings = numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.mapping(
                                num -> "Number: " + num,
                                Collectors.toList()
                        )
                ));
        System.out.println("Number to Strings: " + numberToStrings);

        // Advanced Use Case: filtering
        Map<Boolean, List<Integer>> oddAndEvenNumbers = numbers.stream()
                .collect(Collectors.partitioningBy(
                        num -> num % 2 == 0,
                        Collectors.filtering(
                                num -> num > 2,
                                Collectors.toList()
                        )
                ));
        System.out.println("Odd and Even Numbers: " + oddAndEvenNumbers);

        // Advanced Use Case: teeing
        double averageAndSum = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(
                        Collectors.averagingInt(Integer::intValue),
                        Collectors.summingInt(Integer::intValue),
                        (avg, s) -> avg + s
                ));
        System.out.println("Average + Sum: " + averageAndSum);
    }
}
