package com.andy.part2_functional_interfaces.predefined;

import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        // Define a Consumer to print a message
        Consumer<String> printMessage = message -> System.out.println("Message: " + message);

        // Apply the Consumer to a String
        String message = "Hello, Consumer!";
        printMessage.accept(message);
    }
}
