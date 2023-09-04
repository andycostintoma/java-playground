package com.andy.part4_streams.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class TransformingElementsExample {

    record User(String name, String group, UUID id) {
    }

    public static void main(String[] args) {

        // Example Data
        List<User> users = Arrays.asList(
                new User("John", "Group1", UUID.randomUUID()),
                new User("Jane", "Group2", UUID.randomUUID()),
                new User("Alice", "Group1", UUID.randomUUID()),
                new User("Bob", "Group2", UUID.randomUUID())
        );

        // Grouping by User group and mapping to Set of UUIDs
        Map<String, Set<UUID>> lookup = users.stream()
                .collect(Collectors.groupingBy(User::group,
                        Collectors.mapping(User::id, Collectors.toSet())));

        System.out.println(lookup);
    }
}


