package com.andy.parralelism.basics;

import java.util.Arrays;

/**
 * This class demonstrates the sequential merge sort algorithm for sorting an integer array.
 * Merge sort is a divide-and-conquer algorithm that recursively divides the input array
 * into smaller subarrays, sorts them, and then merges the sorted subarrays to produce
 * the final sorted array.
 */
public class SequentialMergeSort {

    /**
     * Recursively sorts an array using the merge sort algorithm.
     *
     * @param arr   The integer array to be sorted.
     * @param left  The left index of the subarray.
     * @param right The right index of the subarray.
     */
    public static void mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     *
     * @param arr    The integer array containing both subarrays.
     * @param left   The left index of the first subarray.
     * @param middle The right index of the first subarray.
     * @param right  The right index of the second subarray.
     */
    public static void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArr = Arrays.copyOfRange(arr, left, middle + 1);
        int[] rightArr = Arrays.copyOfRange(arr, middle + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        System.out.println("Original array: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
