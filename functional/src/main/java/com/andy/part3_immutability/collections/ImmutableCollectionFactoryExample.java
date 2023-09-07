package com.andy.part3_immutability.collections;

import java.util.List;
import java.util.Set;
import java.util.Map;

public class ImmutableCollectionFactoryExample {
    public static void main(String[] args) {
        // Creating an immutable List
        List<String> colorsList = List.of("red", "green", "blue");
        System.out.println("Immutable List: " + colorsList);

        // Creating an immutable Set
        Set<Integer> numbersSet = Set.of(1, 2, 3, 4, 5);
        System.out.println("Immutable Set: " + numbersSet);

        // Creating an immutable Map
        Map<String, Integer> scoresMap = Map.of("Alice", 95, "Bob", 87, "Charlie", 78);
        System.out.println("Immutable Map: " + scoresMap);
    }
}
