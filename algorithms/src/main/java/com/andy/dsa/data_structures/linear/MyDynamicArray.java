package com.andy.dsa.data_structures.linear;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDynamicArray<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] array;
    private int size;
    private int capacity;

    public MyDynamicArray(int capacity) {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = capacity;
    }

    public MyDynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    // Access an element by index: O(1) time complexity, O(1) space complexity
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return array[index];
    }

    // Add an element to the end of the array: O(1) average time complexity, O(n) worst-case time complexity, O(1) space complexity
    public void add(T element) {
        if (size == capacity) {
            resizeArray();
        }
        array[size++] = element;
    }

    // Add an element at the specified index: O(n) time complexity, O(1) space complexity
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (size == capacity) {
            resizeArray();
        }

        // Shift elements to the right
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size++;
    }

    // Update value at the specified index: O(1) time complexity, O(1) space complexity
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        array[index] = element;
    }

    // Remove an element by value: O(n) time complexity, O(1) space complexity
    public void remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            removeAt(index);
        }
    }

    // Remove an element by index: O(n) time complexity, O(1) space complexity
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null; // Remove the reference to the last element
        size--;
    }

    // Get the index of an element: O(n) time complexity, O(1) space complexity
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1; // Element not found
    }

    // Private method to resize the internal array
    private void resizeArray() {
        int newCapacity = (int) (capacity * GROWTH_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newCapacity;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyDynamicArray{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new DynamicArrayIterator();
    }

    private class DynamicArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the array");
            }
            return array[currentIndex++];
        }
    }
}
