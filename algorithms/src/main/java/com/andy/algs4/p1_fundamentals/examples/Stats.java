package com.andy.algs4.p1_fundamentals.examples;


import edu.princeton.cs.algs4.p1_fundamentals.p3_bags_queues_stacks.Bag;
import edu.princeton.cs.algs4.utils.StdIn;
import edu.princeton.cs.algs4.utils.StdOut;

/******************************************************************************
 *  Reads in a sequence of real numbers from standard input and
 *  computes their mean and standard deviation.
 ******************************************************************************/

public class Stats {
    public static void main(String[] args) {

        // read in numbers
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        int n = numbers.size();

        // compute sample mean
        double sum = 0.0;
        for (double x : numbers)
            sum += x;
        double mean = sum / n;

        // compute sample standard deviation
        sum = 0.0;
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double stdDev = Math.sqrt(sum / (n - 1));

        StdOut.printf("Mean:    %.2f\n", mean);
        StdOut.printf("Std dev: %.2f\n", stdDev);
    }
}
