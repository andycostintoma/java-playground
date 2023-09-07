package com.andy.part4_streams.basics;

import java.util.List;
import java.util.stream.Stream;

public class AggregationDirectlyExample {

    public static void main(String[] args) {
        List<String> fruitList = Stream.of("apple", "orange", "banana", "peach")
                .toList();
        System.out.println("Fruit List: " + fruitList);

        String[] fruitArray = Stream.of("apple", "orange", "banana", "peach")
                .toArray(String[]::new);
        System.out.print("Fruit Array: [");
        for (String fruit : fruitArray) {
            System.out.print(fruit + ", ");
        }
        System.out.println("]");
    }
}
