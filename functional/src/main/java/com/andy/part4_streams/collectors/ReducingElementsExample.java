package com.andy.part4_streams.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class ReducingElementsExample {

    record User(String name, String group, UUID id, List<LogEntry> logEntries) {
    }

    record LogEntry(String message) {
    }

    public static void main(String[] args) {

        // Example Data
        List<User> users = Arrays.asList(
                new User("John", "Group1", UUID.randomUUID(), List.of(new LogEntry("Log 1"), new LogEntry("Log 2"))),
                new User("Jane", "Group2", UUID.randomUUID(), List.of(new LogEntry("Log 3"))),
                new User("Alice", "Group1", UUID.randomUUID(), List.of(new LogEntry("Log 4"), new LogEntry("Log 5"))),
                new User("Bob", "Group2", UUID.randomUUID(), List.of(new LogEntry("Log 6")))
        );

        // Counting log entries per User
        var downstream =
                Collectors.reducing(0,
                        (User user) -> user.logEntries().size(),
                        Integer::sum);

        Map<UUID, Integer> logCountPerUserId = users.stream()
                .collect(Collectors.groupingBy(User::id, downstream));

        System.out.println(logCountPerUserId);
    }
}
