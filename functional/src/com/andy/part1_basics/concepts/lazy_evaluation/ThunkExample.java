package com.andy.part1_basics.concepts.lazy_evaluation;

import java.util.function.Supplier;

/**
 * This class demonstrates the concept of lazy evaluation using Thunks.
 * Thunks are wrappers around computations that are delayed until the result is needed.
 * They allow for efficient memoization, ensuring that an expression is evaluated only once.
 */

public class ThunkExample {

    public static void main(String[] args) {
        // Create a Thunk for an expensive calculation
        Thunk<Integer> lazyCalculation = Thunk.of(() -> {
            try {
                System.out.println("Performing expensive calculation...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        });

        // Access the result multiple times
        System.out.println("First access: " + lazyCalculation.get());
        System.out.println("Second access: " + lazyCalculation.get());
    }

    static class Thunk<T> implements Supplier<T> {

        private record Holder<T>(T value) implements Supplier<T> {
            @Override
                    public T get() {
                        return this.value;
                    }
                }

        private Supplier<T> holder;

        private Thunk(Supplier<T> expression) {
            this.holder = () -> evaluate(expression);
        }

        private synchronized T evaluate(Supplier<T> expression) {
            if (!(this.holder instanceof Holder)) {
                T evaluated = expression.get();
                this.holder = new Holder<>(evaluated);
            }
            return this.holder.get();
        }

        @Override
        public T get() {
            return this.holder.get();
        }

        public static <T> Thunk<T> of(Supplier<T> expression) {
            if (expression instanceof Thunk) {
                return (Thunk<T>) expression;
            }
            return new Thunk<>(expression);
        }

        public static <T> Thunk<T> of(T value) {
            return new Thunk<>(() -> value);
        }
    }
}
