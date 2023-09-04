package com.andy.part3_immutability.records;

import java.time.LocalDateTime;
import java.util.Objects;


public class RecordConstructorsExample {

    record User(String username, boolean active, LocalDateTime lastLogin) {

        // Canonical Constructor (automatically generated)
        // public User(String username, boolean active, LocalDateTime lastLogin) { ... }

        // Compact Constructor
        public User {
            Objects.requireNonNull(username);
            Objects.requireNonNull(lastLogin);
        }

        // Custom Constructor
        public User(String username, LocalDateTime lastLogin) {
            this(username.toLowerCase(), true, lastLogin); // Invoke canonical constructor
        }
    }

    public static void main(String[] args) {
        // Using compact constructor
        User user1 = new User("Alice", false, LocalDateTime.now());
        System.out.println(user1);

        // Using custom constructor
        User user2 = new User("Bob", LocalDateTime.now().minusDays(1));
        System.out.println(user2);
    }
}
