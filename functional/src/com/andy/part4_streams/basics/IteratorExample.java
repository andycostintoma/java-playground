package com.andy.part4_streams.basics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Create an iterator from the list
        Iterator<String> iterator = fruits.iterator();

        // Iterate through the elements using the iterator
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println(fruit);
        }

        // Iterate through the elements using the for-each loop
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
