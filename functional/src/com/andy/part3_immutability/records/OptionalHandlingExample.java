package com.andy.part3_immutability.records;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


public class OptionalHandlingExample {

    record User(String username,
                boolean active,
                Optional<String> group,
                LocalDateTime lastLogin) {

        // Compact constructor for validation
        public User {
            Objects.requireNonNull(group, "Optional<String> group must not be null");
        }

        // Convenience constructor for non-Optional<T> arguments
        public User(String username,
                    boolean active,
                    String group,
                    LocalDateTime lastLogin) {
            this(username,
                    active,
                    Optional.ofNullable(group),
                    lastLogin);
        }

    }

    public static void main(String[] args) {
        // Using compact constructor
        User user1 = new User("Alice", true, Optional.of("Admin"), LocalDateTime.now());
        System.out.println(user1);

        // Using convenience constructor with Optional<String> argument
        User user2 = new User("Bob", true, "Manager", LocalDateTime.now());
        System.out.println(user2);

        // Using convenience constructor with null for Optional<String>
        User user3 = new User("Charlie", false, (String) null, LocalDateTime.now());
        System.out.println(user3);
    }
}
