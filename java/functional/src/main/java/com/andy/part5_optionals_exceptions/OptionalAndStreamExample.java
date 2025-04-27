package com.andy.part5_optionals_exceptions;

import java.util.*;
import java.util.function.Predicate;

/**
 * This program demonstrates the usage of Optionals and Streams in Java.
 * It covers the incorporation of Optionals as stream elements, terminal Stream operations
 * with Optionals, and the advantages of using Optionals for stream processing.
 */
public class OptionalAndStreamExample {

    public static void main(String[] args) {
        // Incorporating Optionals as Stream elements
        List<Permissions> permissions = Arrays.asList(
                new Permissions(Arrays.asList("read", "write"), new Group(new User(true, 23))),
                new Permissions(Collections.emptyList(), new Group(null)),
                new Permissions(Collections.singletonList("read"), new Group(new User(false, 47)))
        );

        List<User> activeUsers = permissions.stream()
                .filter(Predicate.not(Permissions::isEmpty))
                .map(Permissions::getGroup)
                .map(Group::getAdmin)
                .flatMap(Optional::stream)
                .filter(User::isActive)
                .toList();

        // Terminal Stream operations with Optionals
        Optional<User> firstActiveUser = activeUsers.stream().findFirst();
        Optional<User> anyActiveUser = activeUsers.stream().findAny();

        boolean hasActiveUser = activeUsers.stream().anyMatch(User::isActive);
        boolean noInactiveUser = activeUsers.stream().allMatch(User::isActive);


        Optional<User> minUser = activeUsers.stream().min(Comparator.comparingInt(User::age));
        Optional<User> maxUser = activeUsers.stream().max(Comparator.comparingInt(User::age));

        System.out.println("First Active User: " + firstActiveUser.orElse(null));
        System.out.println("Any Active User: " + anyActiveUser.orElse(null));
        System.out.println("Has Active User: " + hasActiveUser);
        System.out.println("No Inactive User: " + noInactiveUser);
        System.out.println("Min Age User: " + minUser.orElse(null));
        System.out.println("Max Age User: " + maxUser.orElse(null));
    }

    static class Permissions {
        List<String> permissions;
        Group group;

        public Permissions(List<String> permissions, Group group) {
            this.permissions = permissions;
            this.group = group;
        }

        public boolean isEmpty() {
            return permissions.isEmpty();
        }

        public Group getGroup() {
            return group;
        }
    }

    static class Group {
        User admin;

        public Group(User admin) {
            this.admin = admin;
        }

        public Optional<User> getAdmin() {
            return Optional.ofNullable(this.admin);
        }
    }

    record User(boolean isActive, int age) {
    }
}
