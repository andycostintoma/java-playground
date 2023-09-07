package com.andy.part1_basics.concepts;

import java.time.LocalTime;

public class PureVsImpureFunctions {


    record User(String name) {
    }

    // Impure function: Relies on hidden state (current time)
    String buildGreetingImpure(User user) {
        String greeting;
        if (LocalTime.now().getHour() < 12) {
            greeting = "Good morning";
        } else {
            greeting = "Hello";
        }
        return String.format("%s, %s", greeting, user.name());
    }

    /*
    Pure function: All dependencies are explicit arguments
        This function is pure because it follows the principles of a pure function:
        1. It has a consistent output for the same input.
        2. It doesn't modify external state.
        3. Its result depends solely on the input parameter.
    */
    String buildGreetingPure(User user, LocalTime time) {
        String greeting;
        if (time.getHour() < 12) {
            greeting = "Good morning";
        } else {
            greeting = "Hello";
        }
        return String.format("%s, %s", greeting, user.name());
    }

    public static void main(String[] args) {

        User user = new User("Alice");
        LocalTime currentTime = LocalTime.of(10, 0);

        PureVsImpureFunctions example = new PureVsImpureFunctions();

        // Impure function call
        String impureGreeting = example.buildGreetingImpure(user);
        System.out.println("Impure Greeting: " + impureGreeting);

        // Pure function call
        String pureGreeting = example.buildGreetingPure(user, currentTime);
        System.out.println("Pure Greeting: " + pureGreeting);
    }
}

