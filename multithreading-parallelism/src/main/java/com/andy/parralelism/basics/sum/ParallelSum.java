package com.andy.parralelism.basics.sum;

/**
 * This class demonstrates a parallel summation of an integer array using multiple threads.
 * The array is divided into chunks, and each thread calculates the sum of a chunk.
 * Finally, the partial sums are combined to calculate the overall sum.
 */
public class ParallelSum {


    public static long parallelSum(long[] arr, int numOfThreads) {
        int chunkSize = arr.length / numOfThreads; // Calculate the chunk size for each thread
        long[] partialSums = new long[numOfThreads]; // Array to store partial sums

        // Create and start threads to calculate partial sums
        Thread[] threads = new Thread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                int start = threadIndex * chunkSize; // Start index for this thread
                int end = (threadIndex == numOfThreads - 1) ? arr.length : start + chunkSize; // End index

                long partialSum = 0; // Initialize a partial sum for this thread

                // Calculate the sum of the elements in the assigned chunk
                for (int j = start; j < end; j++) {
                    partialSum += arr[j];
                }

                partialSums[threadIndex] = partialSum; // Store the partial sum
            });
            threads[i].start(); // Start the thread
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        // Calculate the final sum by combining partial sums
        return SequentialSum.sequentialSum(partialSums); // Return the final sum
    }

    public static void main(String[] args) {

        long[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int numOfThreads = Runtime.getRuntime().availableProcessors(); // Number of available processors

        long sum = parallelSum(arr, numOfThreads); // Call parallelSum to calculate the sum

        System.out.println("Parallel Sum: " + sum);
    }
}
