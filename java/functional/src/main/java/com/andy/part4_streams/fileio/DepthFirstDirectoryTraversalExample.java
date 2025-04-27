package com.andy.part4_streams.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class DepthFirstDirectoryTraversalExample {

    public static void main(String[] args) {

        // Use the current working directory as the starting point
        Path start = Path.of("");

        try (var stream = Files.walk(start)) {
            stream.map(Path::toFile)
                  .filter(Predicate.not(File::isFile))
                  .sorted()
                  .forEach(System.out::println);
        } catch (IOException e) {
            // Handle exception
            System.out.println(e.getMessage());
        }
    }
}
