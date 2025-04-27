package com.andy.parralelism.comparasion;

import com.andy.parralelism.basics.ParallelMergeSort;
import com.andy.parralelism.basics.SequentialMergeSort;
import com.andy.parralelism.fork_join.ForkJoinMergeSort;
import com.andy.parralelism.parallel_streams.ParallelStreamSort;

import java.util.Arrays;
import java.util.Random;

public class MergeSortComparison {

    private static final int ARRAY_SIZE = 100_000_00;

    public static void main(String[] args) {

        int[] arrSequential = generateRandomArray(ARRAY_SIZE);
        int[] arrParallel = Arrays.copyOf(arrSequential, arrSequential.length);
        int[] arrForkJoin = Arrays.copyOf(arrSequential, arrSequential.length);
        int[] arrParallelStream = Arrays.copyOf(arrSequential, arrSequential.length);

        long startTime, endTime;

        // Sequential Merge Sort
        startTime = System.currentTimeMillis();
        SequentialMergeSort.mergeSort(arrSequential, 0, arrSequential.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Sequential Merge Sort took " + (endTime - startTime) + " milliseconds.");


        // Parallel Merge Sort
        startTime = System.currentTimeMillis();
        ParallelMergeSort.parallelMergeSort(arrParallel, 0, arrParallel.length - 1, Runtime.getRuntime().availableProcessors());
        endTime = System.currentTimeMillis();
        System.out.println("Parallel Merge Sort took " + (endTime - startTime) + " milliseconds.");

        // ForkJoin Merge Sort
        startTime = System.currentTimeMillis();
        ForkJoinMergeSort.parallelMergeSort(arrForkJoin, 10_000);
        endTime = System.currentTimeMillis();
        System.out.println("ForkJoin Merge Sort took " + (endTime - startTime) + " milliseconds.");

        // Parallel Stream Sort
        startTime = System.currentTimeMillis();
        ParallelStreamSort.parallelStreamSort(arrParallelStream);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel Stream Sort took " + (endTime - startTime) + " milliseconds.");
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size); // Generate random numbers up to 10,000,000
        }
        return arr;
    }
}