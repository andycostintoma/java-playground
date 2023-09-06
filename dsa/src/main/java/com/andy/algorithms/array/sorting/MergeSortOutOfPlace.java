package com.andy.algorithms.array.sorting;

import java.util.Arrays;

public class MergeSortOutOfPlace {

    private static int[] mergeSort(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }

        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right){
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6 };

        System.out.println("Original array: " + Arrays.toString(arr));
        int[] sorted = mergeSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(sorted));
    }
}
