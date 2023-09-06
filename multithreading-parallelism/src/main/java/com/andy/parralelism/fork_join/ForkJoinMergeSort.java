package com.andy.parralelism.fork_join;

import com.andy.parralelism.basics.SequentialMergeSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinMergeSort {

    static class MergeTask extends RecursiveAction {
        private final int[] arr;
        private final int left;
        private final int right;
        private final int threshold;

        public MergeTask(int[] arr, int left, int right, int threshold) {
            this.arr = arr;
            this.left = left;
            this.right = right;
            this.threshold = threshold;
        }

        @Override
        protected void compute() {
            if (right - left <= threshold) {
                // Use sequential merge sort when the array size is below or equal to the threshold
                SequentialMergeSort.mergeSort(arr, left, right);
            } else {
                // Split the task into two subtasks
                int middle = left + (right - left) / 2;

                MergeTask leftTask = new MergeTask(arr, left, middle, threshold);
                MergeTask rightTask = new MergeTask(arr, middle + 1, right, threshold);

                invokeAll(leftTask, rightTask);

                // Merge the sorted subarrays
                SequentialMergeSort.merge(arr, left, middle, right);
            }
        }
    }

    public static void parallelMergeSort(int[] arr, int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MergeTask(arr, 0, arr.length - 1, threshold));
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        System.out.println("Original array: " + Arrays.toString(arr));
        parallelMergeSort(arr, 3);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
