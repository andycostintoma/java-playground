package com.andy.parralelism.parallel_streams;

import java.util.stream.LongStream;

public class ParallelStreamSum {

    public static long parallelStreamSum(long[] arr) {

        return LongStream.of(arr)
                .parallel()
                .reduce(0, Long::sum);

    }

    public static void main(String[] args) {
        long[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long sum = parallelStreamSum(arr);
        System.out.println("Parallel Sum: " + sum);
    }
}
