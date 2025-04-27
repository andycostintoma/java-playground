package com.andy.part1_basics.concepts;

import java.time.LocalTime;

/**
 * <p>Favoring expressions over statements is a fundamental principle in functional programming to reduce side effects and create safer and more predictable code. In functional programming, expressions are preferred because they evaluate their input to produce output, whereas statements perform actions and often result in side effects.</p>
 *
 * <p>The advantages of favoring expressions include:</p>
 *
 * <ul>
 *     <li>Pure expressions, like pure functions, do not have any side effects.</li>
 *     <li>Expressions can be defined in code, allowing for more explicit and predictable behavior.</li>
 *     <li>Pure expressions consistently yield the same output for the same input, enabling caching techniques like memoization.</li>
 *     <li>Small expressions can be composed to solve larger tasks, promoting code modularity.</li>
 * </ul>
 *
 * <p>One common area where expressions are favored over statements is in control flow, particularly with if-else statements. Using the ternary operator, you can make code more concise and straightforward. For example, the buildGreeting method can be improved as follows:</p>
 */
public class FavorExpressionsOverStatements {

    record User(String name) {
    }


    String buildGreeting(User user, LocalTime time) {
        String greeting = time.getHour() < 12 ? "Good Morning" : "Hello";

        return String.format("%s, %s", greeting, user.name());
    }

    public static void main(String[] args) {
        FavorExpressionsOverStatements example = new FavorExpressionsOverStatements();
        User user = new User("Alice");
        LocalTime morningTime = LocalTime.of(9, 0);
        LocalTime afternoonTime = LocalTime.of(14, 30);

        String morningGreeting = example.buildGreeting(user, morningTime);
        String afternoonGreeting = example.buildGreeting(user, afternoonTime);

        System.out.println("Morning Greeting: " + morningGreeting);
        System.out.println("Afternoon Greeting: " + afternoonGreeting);
    }
}


