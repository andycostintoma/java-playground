package com.andy.part5_optionals_exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class demonstrates the concept of handling errors as values using a Result type.
 * <p>
 * In Java, checked exceptions can complicate functional programming. This example introduces
 * a Result type to elegantly handle success and failure outcomes without throwing exceptions.
 * The Result type provides methods for mapping, handling, and providing fallback values based on
 * the success or failure of an operation.
 * </p>
 * <p>
 * The Result type contains two components: the success value (if the operation succeeds)
 * and the throwable (if the operation fails). It also indicates whether the operation was successful.
 * This allows for clean and expressive error handling without relying on checked exceptions.
 * </p>
 */

public class ErrorAsValueExample {

    record Result<V, E extends Throwable>(V value,
                                          E throwable,
                                          boolean isSuccess) {

        public static <V, E extends Throwable> Result<V, E> success(V value) {
            return new Result<>(value, null, true);
        }

        public static <V, E extends Throwable> Result<V, E> failure(E throwable) {
            return new Result<>(null, throwable, false);
        }

        public <R> Optional<R> mapSuccess(Function<V, R> fn) {
            return this.isSuccess ? Optional.ofNullable(this.value).map(fn)
                    : Optional.empty();
        }

        public <R> Optional<R> mapFailure(Function<E, R> fn) {
            return this.isSuccess ? Optional.empty()
                    : Optional.ofNullable(this.throwable).map(fn);
        }

        public <R> R map(Function<V, R> successFn,
                         Function<E, R> failureFn) {
            return this.isSuccess ? successFn.apply(this.value)
                    : failureFn.apply(this.throwable);
        }

        public void ifSuccess(Consumer<? super V> action) {
            if (this.isSuccess) {
                action.accept(this.value);
            }
        }

        public void ifFailure(Consumer<? super E> action) {
            if (!this.isSuccess) {
                action.accept(this.throwable);
            }
        }

        public void handle(Consumer<? super V> successAction,
                           Consumer<? super E> failureAction) {
            if (this.isSuccess) {
                successAction.accept(this.value);
            } else {
                failureAction.accept(this.throwable);
            }
        }

        public V orElse(V other) {
            return this.isSuccess ? this.value
                    : other;
        }

        public V orElseGet(Supplier<? extends V> otherSupplier) {
            return this.isSuccess ? this.value
                    : otherSupplier.get();
        }

        @SuppressWarnings("unchecked")
        private <E extends Throwable> void sneakyThrow(Throwable e) throws E {
            throw (E) e;
        }

        public V orElseThrow() {
            if (!this.isSuccess) {
                sneakyThrow(this.throwable);
                return null;
            }

            return this.value;
        }
    }


    public static void main(String[] args) {

        Path path1 = Path.of("Path1");
        Path path2 = Path.of("Path2");
        Path path3 = Path.of("Path3");

        // HANDLE ONLY SUCCESS CASE
        Stream.of(path1, path2, path3)
                .map(ErrorAsValueExample::safeReadString)
                .map(result -> result.mapSuccess(String::toUpperCase))
                .flatMap(Optional::stream)
                .forEach(System.out::println);


        // HANDLE BOTH CASES
        var result = safeReadString(path1).map(
                String::toUpperCase,
                failure -> "IO-Error: " + failure.getMessage()
        );
    }

    private static Result<String, IOException> safeReadString(Path path) {
        try {
            return Result.success(Files.readString(path));
        } catch (IOException e) {
            return Result.failure(e);
        }
    }
}