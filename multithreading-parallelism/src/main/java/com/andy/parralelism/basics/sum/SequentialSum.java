package com.andy.parralelism.basics.sum;

public class SequentialSum {

    // Method to calculate the sum of an array
    public static long sequentialSum(long[] arr) {
        long sum = 0; // Initialize a variable to store the sum

        // Iterate through the array and accumulate the sum
        for (long num : arr) {
            sum += num;
        }

        return sum; // Return the sum
    }

    public static void main(String[] args) {
        long[] arr = {1, 2, 3, 4, 5};

        long sum = sequentialSum(arr); // Call the method to calculate the sum

        System.out.println("Sequential Sum: " + sum);
    }
}
