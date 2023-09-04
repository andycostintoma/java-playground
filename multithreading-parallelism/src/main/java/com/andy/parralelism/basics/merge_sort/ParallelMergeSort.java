package com.andy.parralelism.basics.merge_sort;

import java.util.Arrays;

/**
 * This class demonstrates the parallel merge sort algorithm for sorting an integer array.
 * Parallel merge sort divides the input array into smaller subarrays and sorts them concurrently
 * using multiple threads. It then merges the sorted subarrays to produce the final sorted array.
 */
public class ParallelMergeSort {

    /**
     * Performs parallel merge sort on an integer array.
     *
     * @param arr           The integer array to be sorted.
     * @param left          The left index of the subarray.
     * @param right         The right index of the subarray.
     * @param numOfThreads  The number of threads to use for parallel sorting.
     */
    public static void parallelMergeSort(int[] arr, int left, int right, int numOfThreads) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        // Divide the work among threads
        if (numOfThreads > 1) {
            Thread leftThread = new Thread(() -> parallelMergeSort(arr, left, middle, numOfThreads / 2));
            Thread rightThread = new Thread(() -> parallelMergeSort(arr, middle + 1, right, numOfThreads / 2));
            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // If only one thread is allowed, use sequential merge sort
            SequentialMergeSort.mergeSort(arr, left, middle);
            SequentialMergeSort.mergeSort(arr, middle + 1, right);
        }

        SequentialMergeSort.merge(arr, left, middle, right);
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        System.out.println("Original array: " + Arrays.toString(arr));
        parallelMergeSort(arr, 0, arr.length - 1, Runtime.getRuntime().availableProcessors());
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
