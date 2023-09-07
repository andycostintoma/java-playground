package com.andy.part3_immutability.records;

import java.time.LocalDateTime;

public class BuilderPatternExample {

    record User(String username, boolean active, LocalDateTime lastLogin) {

        static final class Builder {
            private final String username;
            private boolean active = true;
            private LocalDateTime lastLogin;

            Builder(String username) {
                this.username = username;
            }

            Builder active(boolean isActive) {
                this.active = isActive;
                return this;
            }

             Builder lastLogin(LocalDateTime lastLogin) {
                this.lastLogin = lastLogin;
                return this;
            }

            User build() {
                return new User(this.username, this.active, this.lastLogin);
            }
        }
    }

    public static void main(String[] args) {
        User.Builder builder1 = new User.Builder("ben");
        User.Builder builder2 = new User.Builder("tom");

        User user1 = builder1.active(true)
                          .lastLogin(LocalDateTime.now())
                          .build();

        User user2 = builder2.active(false)
                .build();


        System.out.println(user1);
        System.out.println(user2);

    }
}
