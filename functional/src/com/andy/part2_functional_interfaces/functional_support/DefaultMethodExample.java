package com.andy.part2_functional_interfaces.functional_support;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DefaultMethodExample {

    // Define a functional interface with default methods
    interface MyCollection<E> extends Iterable<E> {

        // Default method to create a sequential stream
        default Stream<E> stream() {
            return StreamSupport.stream(spliterator(), false);
        }

        // Default method to create a parallel stream
        default Stream<E> parallelStream() {
            return StreamSupport.stream(spliterator(), true);
        }

        @Override
        Spliterator<E> spliterator(); // Abstract method for providing spliterator
    }

    // Class implementing the functional interface
    static class MyArrayList<E> implements MyCollection<E> {

        private final E[] elements; // Array to hold elements

        public MyArrayList(E[] elements) {
            this.elements = elements;
        }

        // Nested iterator class to iterate through elements
        private class Iterator implements java.util.Iterator<E> {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < elements.length;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return elements[currentIndex++];
            }
        }

        @Override
        public java.util.Iterator<E> iterator() {
            return new Iterator(); // Provide the custom iterator
        }

        @Override
        public Spliterator<E> spliterator() {
            // Create and return a spliterator for the elements array
            return Spliterators.spliterator(elements, 0, elements.length, Spliterator.ORDERED);
        }
    }

    public static void main(String[] args) {
        Integer[] data = { 1, 2, 3, 4, 5 };
        MyArrayList<Integer> list = new MyArrayList<>(data);

        // Create a sequential stream and use forEach to print elements
        Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);

        // Create a parallel stream and use forEachOrdered to print elements
        Stream<Integer> parallelStream = list.parallelStream();
        parallelStream.forEachOrdered(System.out::println);
    }
}
