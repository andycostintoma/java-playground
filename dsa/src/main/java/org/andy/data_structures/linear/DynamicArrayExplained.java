package org.andy.data_structures.linear;

import java.util.Arrays;

public class DynamicArrayExplained<T> {

    private static class DynamicArray<T> {
        private static final int DEFAULT_CAPACITY = 10;
        private static final double GROWTH_FACTOR = 1.5;

        private Object[] array;
        private int size;
        private int capacity;

        public DynamicArray() {
            this.array = new Object[DEFAULT_CAPACITY];
            this.size = 0;
            this.capacity = DEFAULT_CAPACITY;
        }

        // Access an element by index: O(1) time complexity, O(1) space complexity
        private T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index is out of bounds");
            }
            return (T) array[index];
        }

        // Add an element to the end of the array: O(1) average time complexity, O(n) worst-case time complexity, O(1) space complexity
        private void add(T element) {
            if (size == capacity) {
                resizeArray();
            }
            array[size++] = element;
        }

        // Add an element at the specified index: O(n) time complexity, O(1) space complexity
        private void add(int index, T element) {
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
        private void set(int index, T element) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index is out of bounds");
            }
            array[index] = element;
        }

        // Remove an element by value: O(n) time complexity, O(1) space complexity
        private void remove(T element) {
            int index = indexOf(element);
            if (index != -1) {
                removeAt(index);
            }
        }

        // Remove an element by index: O(n) time complexity, O(1) space complexity
        private void removeAt(int index) {
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

        // Clear the array: O(1) time complexity, O(1) space complexity (elements remain in memory)
        private void clear() {
            Arrays.fill(array, null);
            size = 0;
        }

        // Get the index of an element: O(n) time complexity, O(1) space complexity
        private int indexOf(T element) {
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
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            capacity = newCapacity;
        }

        private int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);

        System.out.println("Dynamic Array: " + Arrays.toString(dynamicArray.array) + ", Size: " + dynamicArray.size());

        dynamicArray.add(1, 15);
        dynamicArray.set(2, 25);
        dynamicArray.remove(15);

        System.out.println("Dynamic Array after modifications: " + Arrays.toString(dynamicArray.array) + ", Size: " + dynamicArray.size());

        int elementAtIndex2 = dynamicArray.get(2);
        System.out.println("Element at index 2: " + elementAtIndex2);

        dynamicArray.clear();
        System.out.println("Dynamic Array after clearing: " + Arrays.toString(dynamicArray.array) + ", Size: " + dynamicArray.size());
    }
}
