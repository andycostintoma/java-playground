package com.andy.parralelism.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum {


    static class SumTask extends RecursiveTask<Long> {
        private final long[] arr;
        private final int start;
        private final int end;
        private final int threshold;

        public SumTask(long[] arr, int start, int end, int threshold) {
            this.arr = arr;
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }

        @Override
        protected Long compute() {
            if (end - start <= threshold) {
                // If the chunk size is small enough, calculate the sum directly
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            } else {
                // Split the task into two subtasks
                int mid = (start + end) / 2;
                SumTask left = new SumTask(arr, start, mid, threshold);
                SumTask right = new SumTask(arr, mid, end, threshold);

                // Fork both subtasks
                left.fork();
                right.fork();

                // Join and combine the results of subtasks
                return left.join() + right.join();
            }
        }
    }

    public static long forkJoinSum(long[] arr,int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new SumTask(arr, 0, arr.length, threshold));
    }

    public static void main(String[] args) {
        long[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long sum = forkJoinSum(arr, 3);
        System.out.println("Parallel Sum: " + sum);
    }
}
