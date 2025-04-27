package com.andy.part5_optionals_exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Demonstrates safe method extraction to handle checked exceptions in functional code.
 */
public class SafeMethodExtraction {

    public static void main(String[] args) {
        Path path1 = Path.of("Path1");
        Path path2 = Path.of("Path2");
        Path path3 = Path.of("Path3");

        Stream.of(path1, path2, path3)
                .map(SafeMethodExtraction::safeReadString)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    /**
     * Safely reads the content of a file, handling IOException by returning null.
     *
     * @param path The path to the file to read.
     * @return The content of the file as a string, or null if an IOException occurs.
     */
    private static String safeReadString(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            return null;
        }
    }
}
