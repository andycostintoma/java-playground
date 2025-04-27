package com.andy.parralelism.parallel_streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParallelStreamSort {

    public static void parallelStreamSort(int[] arr) {
        int[] sortedArray = IntStream.of(arr)
                .parallel()
                .sorted()
                .toArray();

        System.arraycopy(sortedArray, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        System.out.println("Original array: " + Arrays.toString(arr));
        parallelStreamSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
