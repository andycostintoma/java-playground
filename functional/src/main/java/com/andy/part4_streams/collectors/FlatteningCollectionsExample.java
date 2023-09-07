package com.andy.part4_streams.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class FlatteningCollectionsExample {

    record User(String name, String group, List<String> logEntries) {
    }

    public static void main(String[] args) {

        // Example Data
        List<User> users = Arrays.asList(
                new User("John", "Group1", List.of("Log 1", "Log 2")),
                new User("Jane", "Group2", List.of("Log 3")),
                new User("Alice", "Group1", List.of("Log 4", "Log 5")),
                new User("Bob", "Group2", List.of("Log 6"))
        );

        // Grouping log entries by group with flattening
        var downstream =
                Collectors.flatMapping((User user) -> user.logEntries().stream(),
                        Collectors.toList());

        Map<String, List<String>> result = users.stream()
                .collect(Collectors.groupingBy(User::group, downstream));

        System.out.println(result);
    }
}
