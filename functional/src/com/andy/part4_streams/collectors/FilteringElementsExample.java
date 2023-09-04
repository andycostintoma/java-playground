package com.andy.part4_streams.collectors;

import java.time.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringElementsExample {

    record User(String name, String group, LocalDateTime lastLogin, UUID id) {
    }

    public static void main(String[] args) {

        // Example Data
        List<User> users = Arrays.asList(
                new User("John", "Group1", LocalDateTime.now().minusDays(1), UUID.randomUUID()),
                new User("Jane", "Group2", LocalDateTime.now(), UUID.randomUUID()),
                new User("Alice", "Group1", LocalDateTime.now(), UUID.randomUUID()),
                new User("Bob", "Group2", LocalDateTime.now().minusDays(2), UUID.randomUUID())
        );

        var startOfDay = LocalDate.now().atStartOfDay();

        Predicate<User> loggedInToday =
                Predicate.not(user -> user.lastLogin().toLocalDate().isBefore(startOfDay.toLocalDate()));

        // WITH INTERMEDIATE FILTER

        Map<String, Set<UUID>> todayLoginsByGroupWithFilterOp =
                users.stream()
                        .filter(loggedInToday)
                        .collect(Collectors.groupingBy(User::group,
                                Collectors.mapping(User::id, Collectors.toSet())));

        System.out.println("With Intermediate Filter: " + todayLoginsByGroupWithFilterOp);


        // WITH COLLECT FILTER

        Map<String, Set<UUID>> todayLoginsByGroupWithFilteringCollector =
                users.stream()
                        .collect(Collectors.groupingBy(User::group,
                                Collectors.filtering(loggedInToday,
                                        Collectors.mapping(User::id, Collectors.toSet()))));

        System.out.println("With Collect Filter: " + todayLoginsByGroupWithFilteringCollector);
    }
}
