package com.andy.part4_streams.basics;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This example demonstrates the use of Spliterator in Java to create a custom Spliterator
 * for a custom data structure and then use it to create a stream for efficient data processing.
 * Spliterators allow for the traversal of elements in a data structure, enabling parallel processing
 * and custom data source handling in streams.
 */

public class CustomSpliteratorExample {

    public static void main(String[] args) {
        // Create a custom data structure
        CustomDataStructure<String> dataStructure = new CustomDataStructure<>();
        dataStructure.add("Alpha");
        dataStructure.add("Beta");
        dataStructure.add("Gamma");

        // Create a Spliterator for the custom data structure
        Spliterator<String> customSpliterator = new CustomSpliterator<>(dataStructure);

        // Create a stream from the Spliterator
        Stream<String> customStream = StreamSupport.stream(customSpliterator, false);

        // Use the custom stream to print elements
        customStream.forEach(System.out::println);
    }

    // Custom data structure
    static class CustomDataStructure<T> {
        private Object[] elements;
        private int size;

        CustomDataStructure() {
            elements = new Object[10];
            size = 0;
        }

        void add(T element) {
            if (size >= elements.length) {
                // Resize the array if needed
                Object[] newElements = new Object[elements.length * 2];
                System.arraycopy(elements, 0, newElements, 0, elements.length);
                elements = newElements;
            }
            elements[size++] = element;
        }

        T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            return (T) elements[index];
        }

        int size() {
            return size;
        }
    }

    // Custom Spliterator for the custom data structure
    static class CustomSpliterator<T> implements Spliterator<T> {
        private final CustomDataStructure<T> dataStructure;
        private int current = 0;

        CustomSpliterator(CustomDataStructure<T> dataStructure) {
            this.dataStructure = dataStructure;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (current < dataStructure.size()) {
                action.accept(dataStructure.get(current++));
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            int currentSize = dataStructure.size();
            if (currentSize <= 1) {
                return null;
            }
            int splitSize = currentSize / 2;
            CustomSpliterator<T> newSpliterator = new CustomSpliterator<>(dataStructure);
            current += splitSize;
            newSpliterator.current = current;
            return newSpliterator;
        }

        @Override
        public long estimateSize() {
            return dataStructure.size() - current;
        }

        @Override
        public int characteristics() {
            return ORDERED | SIZED | SUBSIZED;
        }
    }
}
