package com.andy.parralelism.parallel_streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class CustomSortingWithParallelStreams {

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        System.out.println("Original array: " + Arrays.toString(arr));
        parallelCustomSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public static void parallelCustomSort(int[] arr) {
        // Define a custom comparator for sorting in descending order
        Comparator<Integer> customComparator = (a, b) -> b - a;

        // Convert the array into a parallel stream and use the custom comparator for sorting
        int[] sortedArray = IntStream.of(arr)
                .parallel() // Enable parallel processing
                .boxed()    // Convert int to Integer for using customComparator
                .sorted(customComparator)
                .mapToInt(Integer::intValue) // Convert back to int
                .toArray();

        // Update the original array with the sorted values
        System.arraycopy(sortedArray, 0, arr, 0, arr.length);
    }
}
