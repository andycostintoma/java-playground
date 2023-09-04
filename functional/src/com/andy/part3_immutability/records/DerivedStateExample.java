package com.andy.part3_immutability.records;

import java.time.LocalDateTime;

public class DerivedStateExample {

    public record User(String username, boolean active, LocalDateTime lastLogin) {
        public boolean hasLoggedInAtLeastOnce() {
            return lastLogin != null;
        }
    }

    public static void main(String[] args) {
        User user1 = new User("Alice", true, LocalDateTime.now());
        User user2 = new User("Bob", false, null);

        System.out.println("User 1 has logged in at least once: " + user1.hasLoggedInAtLeastOnce()); // true
        System.out.println("User 2 has logged in at least once: " + user2.hasLoggedInAtLeastOnce()); // false
    }
}
