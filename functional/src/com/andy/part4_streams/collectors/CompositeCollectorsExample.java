package com.andy.part4_streams.collectors;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CompositeCollectorsExample {

    record User(String name, String group, LocalDateTime lastLogin, UUID id) {
    }

    record UserStats(long total, long neverLoggedIn) {
    }

    public static void main(String[] args) {

        // Example Data
        List<User> users = Arrays.asList(
                new User("John", "Group1", LocalDateTime.now().minusDays(1), UUID.randomUUID()),
                new User("Jane", "Group2", LocalDateTime.now(), UUID.randomUUID()),
                new User("Alice", "Group1", LocalDateTime.now(), UUID.randomUUID()),
                new User("Bob", "Group2", null, UUID.randomUUID())
        );

        UserStats result = users.stream()
                .collect(Collectors.teeing(
                        Collectors.counting(),  // First downstream collector: count all users
                        Collectors.filtering(user -> user.lastLogin() == null, Collectors.counting()), // Second downstream collector: count users with null lastLogin
                        UserStats::new)); // Merge the results into UserStats object

        System.out.println("Total Users: " + result.total());
        System.out.println("Users Never Logged In: " + result.neverLoggedIn());
    }
}
