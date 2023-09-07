package com.andy.part3_immutability.records;

import java.util.List;

public class IncreaseImmutabilityExample {

    public record IncreaseImmutability(List<String> values) {

        public IncreaseImmutability {
            values = List.copyOf(values);
        }
    }

    public static void main(String[] args) {

        List<String> originalList = List.of("apple", "banana", "cherry");
        IncreaseImmutability immutabilityExample = new IncreaseImmutability(originalList);

        System.out.println("Original list: " + originalList);
        System.out.println("Record values: " + immutabilityExample.values());

        // Try modifying the original list
        // originalList.add("date"); // This will throw an UnsupportedOperationException

        // Try modifying the record's values
        // immutabilityExample.values().add("date"); // This will also throw an UnsupportedOperationException
    }
}
