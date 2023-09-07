package com.andy.part1_basics.concepts;

import java.time.LocalTime;

/**
 * <p>The concept of pure object methods, where methods on an object are as pure as possible within the object's context, aligns with the principles of pure functions in functional programming.</p>
 *
 * <p>In Java, you can apply this concept by ensuring that methods within an object adhere to the principles of referential transparency and have minimal side effects.</p>
 *
 * <p>Here's an example in Java illustrating the idea of pure object methods:</p>
 */

public class PureObjectMethods {


    public record User(String name) {

        public String buildGreeting(LocalTime time) {
            String greeting;
            if (time.getHour() < 12) {
                greeting = "Good morning";
            } else {
                greeting = "Hello";
            }
            return String.format("%s, %s", greeting, this.name);
        }


        @Override
        public String name() {
            return name;
        }
    }

    public static void main(String[] args) {
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        LocalTime currentTime = LocalTime.of(10, 0);

        // Pure object method calls
        String greeting1 = user1.buildGreeting(currentTime);
        String greeting2 = user2.buildGreeting(currentTime);

        System.out.println("Greeting for " + user1.name() + ": " + greeting1);
        System.out.println("Greeting for " + user2.name() + ": " + greeting2);
    }
}
