package com.andy.part6_design_patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BuilderFP {

    public record User(String email, String name, List<String> permissions) {
        
        public static class Builder {
            private Supplier<String> emailSupplier;
            private String name;
            private final List<String> permissions = new ArrayList<>();

            public Builder email(Supplier<String> emailSupplier) {
                this.emailSupplier = emailSupplier;
                return this;
            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder with(Consumer<Builder> builderFn) {
                builderFn.accept(this);
                return this;
            }

            public Builder withPermissions(Consumer<List<String>> permissionsFn) {
                permissionsFn.accept(this.permissions);
                return this;
            }

            public User build() {
                String email = (emailSupplier != null) ? emailSupplier.get() : null;
                return new User(email, name, permissions);
            }
        }

        public static Builder builder() {
            return new Builder();
        }
    }

    public static void main(String[] args) {
        User user = User.builder()
                .with(builder -> {
                    builder.email(() -> "another@example.com");
                    builder.name("Another User");
                })
                .withPermissions(permissions -> {
                    permissions.add("create");
                    permissions.add("view");
                })
                .build();

        System.out.println("User email: " + user.email());
        System.out.println("User name: " + user.name());
        System.out.println("User permissions: " + user.permissions());
    }
}
