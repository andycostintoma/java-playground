package com.andy.part1_basics.concepts;

/**
 * <p>Embracing immutability is a fundamental principle in functional programming that promotes safer and more predictable code by eliminating unintended changes or side effects. Immutability should be the default approach whenever possible, particularly in concurrent environments. By preventing unexpected mutations, your codebase becomes more robust.</p>
 *
 * <p>Java provides several options for working with immutable data structures:</p>
 *
 * <ul>
 *     <li><b>Immutable Collections:</b> While Java does not provide fully immutable Collection types, it offers structurally immutable ones where you cannot add or remove elements. Java 9 introduced static factory methods like List.of to create structurally immutable Collections.</li>
 *     <li><b>Immutable Math:</b> The java.math package includes two immutable arbitrary-precision types, BigInteger and BigDecimal, suitable for high-precision calculations.</li>
 *     <li><b>Records (JEP 395):</b> Records, introduced as a preview feature in Java 14 and refined in 15, provide an easy-to-use data aggregation type. They are suitable for creating small, localized immutable data holders.</li>
 *     <li><b>Java Date and Time API (JSR-310):</b> Java 8 introduced an immutable way to store and manipulate dates and times with the Java Date and Time API. It offers a fluent, explicit, and straightforward approach to date and time-related operations.</li>
 * </ul>
 *
 * <p>Embracing immutability in your codebase from the beginning simplifies data management and prevents unintended changes. It eliminates concerns about thread safety in concurrent environments. However, it's important to note that immutability is best suited for truly immutable data. Creating new immutable data structures for every change can lead to increased code complexity and memory consumption.</p>
 *
 * <p>Remember that adopting an "immutable first" mindset yields safer and more reasonable data structures. While immutability might introduce new challenges, it's easier to introduce immutability into a codebase when necessary than to retroactively remove it.</p>
 */
public class EmbraceImmutability {

    public static void main(String[] args) {
        EmbraceImmutability example = new EmbraceImmutability();

        // Example of using immutable collections
        example.useImmutableCollections();

        // Example of using records
        example.useRecords();

        // Example of using Java Date and Time API
        example.useDateTimeApi();
    }

    /**
     * <p>Illustrates the use of immutable collections in Java. While Java does not provide fully immutable Collection types,
     * it offers structurally immutable ones where elements cannot be added or removed.</p>
     */
    public void useImmutableCollections() {
        // Create an immutable list
        java.util.List<String> immutableList = java.util.List.of("apple", "banana", "cherry");

        // Attempting to add an element will result in an UnsupportedOperationException
        // immutableList.add("date");
    }

    /**
     * <p>Illustrates the use of records in Java. Records provide a simple and easy-to-use way to define immutable data aggregation types.</p>
     */
    public void useRecords() {
        // Define a record for representing a Point
        record Point(int x, int y) {
        }

        // Create a Point instance (immutable)
        Point p = new Point(10, 20);

        // Attempting to modify the values will result in a compilation error
        // p.x = 30;
        // p.y = 40;
    }

    /**
     * <p>Illustrates the use of the Java Date and Time API (JSR-310) for immutable date and time manipulation.</p>
     */
    public void useDateTimeApi() {
        // Create an immutable LocalDate
        java.time.LocalDate date = java.time.LocalDate.of(2023, 9, 6);

        // Attempting to modify the date will result in a new LocalDate instance
        java.time.LocalDate newDate = date.plusDays(1);

        System.out.println("Original Date: " + date);
        System.out.println("New Date: " + newDate);
    }
}
