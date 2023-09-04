package com.andy.part3_immutability.collections;

import java.util.ArrayList;
import java.util.List;

public class ImmutableCopyExample {
    public static void main(String[] args) {
        // Setup original list
        List<String> original = new ArrayList<>();
        original.add("blue");
        original.add("red");

        // Create an immutable copy of the list
        List<String> copiedList = List.copyOf(original);

        // Add a new item to the original list
        original.add("green");

        // Check content
        System.out.println("Original List: " + original);
        // [blue, red, green]

        System.out.println("Copied List: " + copiedList);
        // [blue, red]
    }
}
