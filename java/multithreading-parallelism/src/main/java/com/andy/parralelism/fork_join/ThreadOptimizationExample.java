package com.andy.parralelism.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * <p><strong> Thread Reuse </strong></p>
 * <p> By forking fib2 and immediately calling fib1.compute(),
 * the current thread can work on fib1 without spawning a new thread for it.
 * This thread reuse reduces the overhead of creating and managing additional threads, which can be costly. </p>
 */

public class ThreadOptimizationExample {

    static class FibonacciTask extends RecursiveTask<Integer> {

        private final int n;

        public FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {

            // F(0) = F(1) = 0
            if (n <= 1)
                return n;

            FibonacciTask fib1 = new FibonacciTask(n - 1);
            FibonacciTask fib2 = new FibonacciTask(n - 2);

            fib2.fork();

            return fib1.compute() + fib2.join();
        }

        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool();
            System.out.println(pool.invoke(new FibonacciTask(25)));
        }
    }
}
