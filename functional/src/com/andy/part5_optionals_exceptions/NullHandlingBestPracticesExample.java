package com.andy.part5_optionals_exceptions;

import java.util.Objects;

/**
 * This class demonstrates best practices for handling null in Java.
 * It covers three important rules for null handling to write safer and more predictable code.
 */

public class NullHandlingBestPracticesExample {

    // Rule 1: Don't initialize a variable to null
    public static String initializeVariable(boolean condition) {
        String value;
        if (condition) {
            value = "Condition is true";
        } else {
            value = "Fallback if false";
        }
        return value;
    }

    // Rule 2: Don't pass, accept, or return null
    public static class User {
        private final String firstname;
        private final String lastname;

        public User(String firstname, String lastname) {
            this.firstname = Objects.requireNonNull(firstname);
            this.lastname = Objects.requireNonNull(lastname);
        }

        public String getFullName() {
            return firstname + " " + lastname;
        }
    }

    // Rule 3: Check everything outside your control
    public static String processExternalData(String data) {
        if (data != null) {
            // Process the data
            return "Processed: " + data;
        } else {
            return "Invalid data";
        }
    }


    public static void main(String[] args) {
        // Example usage of the rules

        // Rule 1: Don't initialize a variable to null
        String initializedValue = initializeVariable(true);
        System.out.println("Initialized Value: " + initializedValue);

        // Rule 2: Don't pass, accept, or return null
        User user = new User("John", "Doe");
        System.out.println("User Full Name: " + user.getFullName());

        // Rule 3: Check everything outside your control
        String externalData = processExternalData("Some data");
        System.out.println("Processed Data: " + externalData);

    }
}
