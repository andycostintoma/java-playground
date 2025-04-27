package com.andy.parralelism.fork_join;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

/**
 * <p><strong>SimpleRecursiveTaskExample:</strong></p>
 *
 * <p><strong>Purpose:</strong> This example demonstrates the usage of the Fork-Join framework in Java for parallel execution of recursive tasks. It shows how to split a large task into smaller subtasks and execute them concurrently using the `RecursiveTask` class and the `ForkJoinPool`.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>The `SimpleRecursiveTask` class extends `RecursiveTask` and represents a task with simulated work.</li>
 *   <li>If the simulated work is substantial (above a threshold), the task is split into smaller subtasks executed in parallel.</li>
 *   <li>For small simulated work, the task is executed sequentially.</li>
 *   <li>The `ForkJoinPool` is used to invoke the root task, which then spawns and manages subtasks.</li>
 * </ul>
 */

public class SimpleRecursiveTaskExample {

    static class SimpleRecursiveTask extends RecursiveTask<Integer> {

        private final int simulatedWork;

        public SimpleRecursiveTask(int simulatedWork) {
            this.simulatedWork = simulatedWork;
        }

        @Override
        protected Integer compute() {
            if (simulatedWork > 100) {
                // If the simulated work is substantial, split the task into smaller subtasks and execute in parallel
                System.out.println(Thread.currentThread().getName() + " -> Parallel execution and split the tasks... " + simulatedWork);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Divide the work into two subtasks
                int halfWork = simulatedWork / 2;
                SimpleRecursiveTask left = new SimpleRecursiveTask(halfWork);
                SimpleRecursiveTask right = new SimpleRecursiveTask(halfWork);

                // Fork and execute both subtasks in parallel
                invokeAll(left, right);

                // Combine results of subtasks
                return left.join() + right.join();
            } else {
                // If the simulated work is small, execute it sequentially
                System.out.println(Thread.currentThread().getName() + " -> Sequential execution for task with size: " + simulatedWork);
                return simulatedWork;
            }
        }
    }

    public static void main(String[] args) {
        // Create a simple recursive task with simulated work of 600
        SimpleRecursiveTask task = new SimpleRecursiveTask(600);
        ForkJoinPool pool = new ForkJoinPool();

        // Start the execution of the task and get the result
        Integer result = pool.invoke(task);
        System.out.println("Result: " + result);
    }
}
