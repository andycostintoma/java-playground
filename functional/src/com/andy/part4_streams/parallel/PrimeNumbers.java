package com.andy.part4_streams.parallel;

import java.util.stream.IntStream;

public class PrimeNumbers {
    public static void main(String[] args) {


        int n = Integer.MAX_VALUE / 100;

        long start = System.currentTimeMillis();
        long numOfPrimes = IntStream.rangeClosed(2, n).filter(PrimeNumbers::isPrime).count();
        long end = System.currentTimeMillis();
        System.out.println("Time took " + (end - start) + " milliseconds.");
        System.out.println("Num of primes sequential: " + numOfPrimes);

        start = System.currentTimeMillis();
        numOfPrimes = IntStream.rangeClosed(2, n).parallel().filter(PrimeNumbers::isPrime).count();
        end = System.currentTimeMillis();
        System.out.println("Time took " + (end - start) + " milliseconds.");
        System.out.println("Num of primes parallel: " + numOfPrimes);


    }

    public static boolean isPrime(long num) {
        if (num == 2) return true;
        if (num <= 1 || num % 2 == 0) return false;

        long maxDivisor = (long) Math.sqrt(num);

        for (int i = 3; i <= maxDivisor; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
