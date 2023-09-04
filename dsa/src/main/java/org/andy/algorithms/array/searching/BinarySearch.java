package org.andy.algorithms.array.searching;

import java.util.Optional;

public class BinarySearch {

    public static Optional<Integer> binarySearch(int[] numbers, int item) {
        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = numbers[mid];
            if (guess == item) {
                return Optional.of(mid);
            } else if (guess > item) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 2));
    }
}
