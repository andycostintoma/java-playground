package com.andy.dsa.data_structures.linear;

import java.util.Arrays;

public class FixedSizeArrayExample {
    private static final int ARRAY_SIZE = 5;
    public static void main(String[] args) {

        double[] initialArray = new double[ARRAY_SIZE];
        System.out.println("Initial array: " + Arrays.toString(initialArray));

        // Assign values to elements
        initialArray[0] = 10.8;
        initialArray[1] = 14.3;
        initialArray[2] = 13.5;
        initialArray[3] = 12.1;
        initialArray[4] = 9.7;

        System.out.println("Array after assigning values: " + Arrays.toString(initialArray));

        // Accessing an element by index: O(1) time complexity, O(1) space complexity
        double thirdMeasurement = initialArray[2];
        System.out.println("Accessing element at index 2: " + thirdMeasurement);

        // Searching for an element: O(n) time complexity, O(1) space complexity
        double searchElement = 13.5;
        int searchIndex = -1;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (initialArray[i] == searchElement) {
                searchIndex = i;
                break;
            }
        }
        System.out.println("Searching for " + searchElement + " at index: " + searchIndex);

        // Inserting an element
        double newElement = 11.0;
        int insertIndex = 2;
        double[] arrayAfterInsertion = insertElement(initialArray, newElement, insertIndex);
        System.out.println("Array after inserting: " + Arrays.toString(arrayAfterInsertion));

        // Deleting an element
        int deleteIndex = 4;
        double[] arrayAfterDeletion = deleteElement(initialArray, deleteIndex);
        System.out.println("Updated array after deleting: " + Arrays.toString(arrayAfterDeletion));
    }

    // Inserting an element: O(n) time complexity (due to copying), O(n) space complexity (new array)
    private static double[] insertElement(double[] array, double element, int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("Index is out of bounds");
        }

        double[] newArray = new double[array.length + 1];

        for (int i = 0, j = 0; i < newArray.length; i++) {
            if (i == index) {
                newArray[i] = element;
            } else {
                newArray[i] = array[j++];
            }
        }

        return newArray;
    }

    // Deleting an element: O(n) time complexity (due to copying), O(n) space complexity (new array)
    private static double[] deleteElement(double[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Index is out of bounds");
        }

        double[] newArray = new double[array.length - 1];

        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                newArray[j++] = array[i];
            }
        }

        return newArray;
    }

}
