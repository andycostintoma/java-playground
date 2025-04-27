package com.andy.parralelism.fork_join;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

/**
 * <p><strong>SimpleRecursiveActionExample:</strong></p>
 *
 * <p><strong>Purpose:</strong> This example demonstrates the usage of the Fork-Join framework in Java for
 * parallel execution of recursive tasks. It shows how to split a large task into smaller subtasks and execute
 * them concurrently using the `RecursiveAction` class and the `ForkJoinPool`.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>The `SimpleRecursiveAction` class extends `RecursiveAction` and represents a task with simulated work.</li>
 *   <li>If the simulated work is substantial (above a threshold), the task is split into smaller subtasks
 *   executed in parallel.</li>
 *   <li>For small simulated work, the task is executed sequentially.</li>
 *   <li>The `ForkJoinPool` is used to invoke the root task, which then spawns and manages subtasks.</li>
 * </ul>
 */

public class SimpleRecursiveActionExample {

    static class SimpleRecursiveAction extends RecursiveAction {

        private final int simulatedWork;
        private static final int THRESHOLD = 100;

        public SimpleRecursiveAction(int simulatedWork) {
            this.simulatedWork = simulatedWork;
        }

        @Override
        protected void compute() {
            if (simulatedWork > THRESHOLD) {
                // If the simulated work is substantial, split the task into smaller subtasks and execute in parallel
                System.out.println(Thread.currentThread().getName() + " -> Parallel execution and split the tasks... " + simulatedWork);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Divide the work into two subtasks
                int halfWork = simulatedWork / 2;
                SimpleRecursiveAction left = new SimpleRecursiveAction(halfWork);
                SimpleRecursiveAction right = new SimpleRecursiveAction(halfWork);

                // Fork and execute both subtasks in parallel
                invokeAll(left, right);

            } else {
                // If the simulated work is small, execute it sequentially
                System.out.println(Thread.currentThread().getName() + " -> Sequential execution for task with size: " + simulatedWork);
            }
        }
    }

    public static void main(String[] args) {
        // Create a simple recursive action with simulated work of 600
        SimpleRecursiveAction action = new SimpleRecursiveAction(600);
        ForkJoinPool pool = new ForkJoinPool();

        // Start the execution of the action
        pool.invoke(action);
    }
}
