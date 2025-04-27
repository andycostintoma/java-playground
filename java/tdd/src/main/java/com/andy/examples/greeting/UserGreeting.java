package com.andy.examples.greeting;

public class UserGreeting {

    private final UserProfiles profiles;

    public UserGreeting(UserProfiles profiles) {
        this.profiles = profiles;
    }

    public String formatGreeting(UserId userId) {
        return String.format("Hello and Welcome, %s", profiles.fetchNicknameFor(userId));
    }
}
