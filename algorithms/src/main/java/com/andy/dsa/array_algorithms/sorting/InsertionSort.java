package com.andy.dsa.array_algorithms.sorting;

import java.util.Arrays;

// O(n^2)
public class InsertionSort {
    private static void insertionSort(int[] arr) {
        int n = arr.length;

        // Iterate through the array, starting from the second element
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // The current element we want to insert into the sorted portion
            int j = i - 1;    // Initialize the index of the last element in the sorted portion

            // Move elements of arr[0...i-1], that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift the element to the right
                j--;                 // Move to the previous element in the sorted portion
            }

            // Place the key in its correct position in the sorted portion
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6 };

        System.out.println("Original array: " + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
