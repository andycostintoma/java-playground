package com.andy.parralelism.comparasion;

import com.andy.parralelism.basics.ParallelSum;
import com.andy.parralelism.basics.SequentialSum;
import com.andy.parralelism.fork_join.ForkJoinSum;
import com.andy.parralelism.parallel_streams.ParallelStreamSum;

import java.util.Random;

public class SumComparison {

    private static final int ARRAY_SIZE = 500_000_000;

    public static void main(String[] args) {
        long[] arr = generateRandomArray(ARRAY_SIZE); // Generate a large random array

        long startTime, endTime;

        // Sequential Sum
        startTime = System.currentTimeMillis();
        long sequentialSum = SequentialSum.sequentialSum(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Sequential Sum: " + sequentialSum);
        System.out.println("Sequential Sum took " + (endTime - startTime) + " milliseconds.");

        // Parallel Sum
        int numOfThreads = Runtime.getRuntime().availableProcessors(); // Number of available processors
        startTime = System.currentTimeMillis();
        long parallelSum = ParallelSum.parallelSum(arr, numOfThreads);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel Sum: " + parallelSum);
        System.out.println("Parallel Sum took " + (endTime - startTime) + " milliseconds.");

        // ForkJoinSum Sum
        startTime = System.currentTimeMillis();
        long forkJoinSum = ForkJoinSum.forkJoinSum(arr, 100_000);
        endTime = System.currentTimeMillis();
        System.out.println("ForkJoin Sum: " + forkJoinSum);
        System.out.println("ForkJoin Sum took " + (endTime - startTime) + " milliseconds.");

        // Parallel Stream Sum
        startTime = System.currentTimeMillis();
        long parallelStreamSum = ParallelStreamSum.parallelStreamSum(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel Stream Sum: " + parallelStreamSum);
        System.out.println("Parallel Stream Sum took " + (endTime - startTime) + " milliseconds.");
    }

    private static long[] generateRandomArray(int size) {
        long[] arr = new long[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size); // Generate random numbers up to ARRAY_SIZE
        }
        return arr;
    }
}
