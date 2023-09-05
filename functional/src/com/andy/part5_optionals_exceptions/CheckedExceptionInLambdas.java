package com.andy.part5_optionals_exceptions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Demonstrates the issue of checked exceptions in lambdas and how they can be handled using a try-catch block.
 */
public class CheckedExceptionInLambdas {

    public static void main(String[] args) {
        Path path1 = Path.of("Path1");
        Path path2 = Path.of("Path2");
        Path path3 = Path.of("Path3");

        // Attempting to use a lambda expression with a method that throws a checked exception
        Stream.of(path1, path2, path3)
              .map(path -> {
                  try {
                      return Files.readString(path);
                  } catch (IOException e) {
                      // Handle the exception, or return a default value, e.g., null
                      return null;
                  }
              })
              .forEach(System.out::println);
    }
}
