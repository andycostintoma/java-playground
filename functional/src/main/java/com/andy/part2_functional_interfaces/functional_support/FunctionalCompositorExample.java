package com.andy.part2_functional_interfaces.functional_support;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalCompositorExample {

    static final class Compositor {

        // Static helper to compose a Supplier<T> and a Function<T, R>
        public static <T, R> Supplier<R> compose(Supplier<T> before, Function<T, R> fn) {
            Objects.requireNonNull(before);
            Objects.requireNonNull(fn);

            return () -> {
                T result = before.get();
                return fn.apply(result);
            };
        }

        // Static helper to compose a Function<T, R> and a Consumer<R>
        public static <T, R> Consumer<T> compose(Function<T, R> fn, Consumer<R> after) {
            Objects.requireNonNull(fn);
            Objects.requireNonNull(after);

            return (T t) -> {
                R result = fn.apply(t);
                after.accept(result);
            };
        }

        // Static helper to accept a value if a Predicate<T> is satisfied, and then apply a Consumer<T>
        public static <T> Consumer<T> acceptIf(Predicate<T> predicate, Consumer<T> consumer) {
            Objects.requireNonNull(predicate);
            Objects.requireNonNull(consumer);

            return (T t) -> {
                if (predicate.test(t)) {
                    consumer.accept(t);
                }
            };
        }

        // Private constructor to prevent instantiation of the helper class
        private Compositor() {
        }
    }

    public static void main(String[] args) {
        // SINGULAR STRING FUNCTIONS
        Function<String, String> removeLowerCaseA = str -> str.replace("a", "");
        Function<String, String> upperCase = String::toUpperCase;

        // COMPOSED STRING FUNCTIONS
        Function<String, String> stringOperations =
                removeLowerCaseA.andThen(upperCase);

        // COMPOSED STRING FUNCTIONS AND CONSUMER
        Consumer<String> task = Compositor.compose(stringOperations, System.out::println);

        // RUNNING TASK
        task.accept("abcd");
        // => BCD

        // Create a Predicate that checks if the string length is even
        Predicate<String> isStringLengthEven = str -> str.length() % 2 == 0;

        // COMPOSED STRING FUNCTIONS AND CONDITIONAL CONSUMER
        Consumer<String> conditionalTask = Compositor.acceptIf(isStringLengthEven, System.out::println);

        // RUNNING CONDITIONAL TASK
        conditionalTask.accept("hello"); // No output, length is not even
        conditionalTask.accept("world"); // Prints "world"
    }
    
    // Comments explaining the creation of static helpers for functional composition:
    // 1. Static helper methods can be added to your types to provide custom functional composition logic.
    // 2. Useful when the default methods provided by JDK's functional interfaces do not cover specific use cases.
    // 3. Static helper methods can combine multiple functional interfaces in a flexible manner.
    // 4. Allows you to bridge the gap between different functional interfaces and provide reusable composition logic.
}
