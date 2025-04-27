package com.andy.part4_streams.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;

public class SearchFilesystemExample {

    public static void main(String[] args) {

        // Use the current working directory as the starting point
        Path start = Path.of("");

        // Create a BiPredicate to filter directories
        BiPredicate<Path, BasicFileAttributes> matcher =
                (path, attr) -> attr.isDirectory();

        try {
            // Use Files.find to create a Stream of Paths that match the filter
            try (var stream = Files.find(start, Integer.MAX_VALUE, matcher)) {
                // Sort and print the matched paths
                stream.sorted()
                        .forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
