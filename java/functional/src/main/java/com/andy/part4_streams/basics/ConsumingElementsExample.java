package com.andy.part4_streams.basics;

import java.util.List;

public class ConsumingElementsExample {

    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "orange", "banana", "peach");

        System.out.println("Using forEach:");
        fruits.stream().forEach(fruit -> System.out.println("Fruit: " + fruit));

        System.out.println("\nUsing forEachOrdered:");
        fruits.stream().forEachOrdered(fruit -> System.out.println("Fruit: " + fruit));
    }
}
