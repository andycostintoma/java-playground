package com.andy.part1_basics.concepts.lazy_evaluation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyStreamExample {

    public static void main(String[] args) {
        List<String> strings = List.of("apple", "banana", "cherry", "date", "elderberry");

        Stream<String> filteredStream = strings.stream()
                .filter(s -> {
                    System.out.println("Filtering: " + s);
                    return s.startsWith("b");
                });

        System.out.println("Stream created, but not evaluated yet.");

        List<String> resultList = filteredStream.toList();

        System.out.println("Terminal operation (collect) triggered evaluation.");

        System.out.println("Result List: " + resultList);
    }
}
