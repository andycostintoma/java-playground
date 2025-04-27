package com.andy.part3_immutability;

public class StringImmutabilityExample {
    public static void main(String[] args) {
        // Creating a String
        String original = "hello";

        // Concatenating Strings creates a new String
        String concatenated = original + " world";

        // Using String methods
        String upperCase = concatenated.toUpperCase();
        String lowerCase = concatenated.toLowerCase();

        // String pooling
        String literal1 = "hello";
        String literal2 = "hello";

        System.out.println("Concatenated String: " + concatenated);
        System.out.println("Upper Case String: " + upperCase);
        System.out.println("Lower Case String: " + lowerCase);

        // Comparing Strings
        System.out.println("Are literal1 and literal2 the same? " + (literal1 == literal2)); // true

        // Using equals for proper equality comparison
        System.out.println("Are literal1 and literal2 equal? " + literal1.equals(literal2)); // true

        // Creating a new String instance
        String newString = new String("hello");

        // Comparing newString with literal1
        System.out.println("Are newString and literal1 the same? " + (newString == literal1)); // false

        // Interning newString to use string pool
        String internedString = newString.intern();

        // Comparing internedString with literal1
        System.out.println("Are internedString and literal1 the same? " + (internedString == literal1)); // true
    }
}
