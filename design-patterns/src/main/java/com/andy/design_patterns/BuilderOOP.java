package com.andy.part6_design_patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuilderOOP {

    record User(String email, String name, List<String> permissions) {

        public User(String email, String name, List<String> permissions) {

            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("'email' must be set.");
            }

            this.email = email;
            this.name = name;
            this.permissions = (permissions != null) ? new ArrayList<>(permissions) : Collections.emptyList();
        }

        @Override
        public List<String> permissions() {
            return Collections.unmodifiableList(permissions);
        }

        static class Builder {
            private String email;
            private String name;
            private final List<String> permissions = new ArrayList<>();

            Builder email(String email) {
                this.email = email;
                return this;
            }

            Builder name(String name) {
                this.name = name;
                return this;
            }

            Builder addPermission(String permission) {
                permissions.add(permission);
                return this;
            }

            User build() {
                return new User(email, name, permissions);
            }
        }

        public static Builder builder() {
            return new Builder();
        }

    }

    public static void main(String[] args) {

        User user = User.builder()
                .email("andy@example.com")
                .name("Andy")
                .addPermission("create")
                .addPermission("edit")
                .build();

        System.out.println("User email: " + user.email());
        System.out.println("User name: " + user.name());
        System.out.println("User permissions: " + user.permissions());
    }
}
