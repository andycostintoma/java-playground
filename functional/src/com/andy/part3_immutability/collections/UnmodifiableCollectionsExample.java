package com.andy.part3_immutability.collections;

import java.util.ArrayList;
import java.util.List;

public class UnmodifiableCollectionsExample {
    public static void main(String[] args) {
        // Creating a modifiable ArrayList
        List<String> modifiable = new ArrayList<>();
        modifiable.add("blue");
        modifiable.add("red");

        // Creating an unmodifiable view of the ArrayList
        List<String> unmodifiable = java.util.Collections.unmodifiableList(modifiable);

        System.out.println("Unmodifiable List: " + unmodifiable);

        // Attempting to modify the unmodifiable list will throw an exception
        try {
            unmodifiable.clear(); // Throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught exception: " + e);
        }

        // The underlying collection list can still be modified
        modifiable.add("green");
        System.out.println("Original List: " + modifiable);
        System.out.println("Unmodifiable List after modification: " + unmodifiable);
    }

    //    The common use for unmodifiable views is to freeze Collections
    //    for unwanted modification before using them as a return value.
}
