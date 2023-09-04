package org.andy.algorithms.array.sorting;

import java.util.Arrays;

// O(n^2)
public class BubbleSort {
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        // Iterate through the entire array
        for (int i = 0; i < n - 1; i++) {
            // Last i elements are already in place, so we don't need to compare them again
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is greater than the next element
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
        
        System.out.println("Original array: " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
