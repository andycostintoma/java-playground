package com.andy.part4_streams.parallel;

import java.util.stream.LongStream;

public class ParallelStreamSum {

    public static void main(String[] args) {
        int n = 500_000_000;

        long start = System.currentTimeMillis();
        long sequentialSum = LongStream.rangeClosed(1, n).reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("Sequential Sum: " + sequentialSum);
        System.out.println("Sequential Sum took " + (end - start) + " milliseconds.");


        start = System.currentTimeMillis();
        long parallelSum = LongStream.rangeClosed(1, n).parallel().reduce(0, Long::sum);
        end = System.currentTimeMillis();
        System.out.println("Parallel Sum: " + parallelSum);
        System.out.println("Parallel Sum took " + (end - start) + " milliseconds.");

    }
}
