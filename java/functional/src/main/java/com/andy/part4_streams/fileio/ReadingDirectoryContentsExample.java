package com.andy.part4_streams.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReadingDirectoryContentsExample {

    public static void main(String[] args) {

        // Use the current working directory for listing
        Path currentDir = Path.of("");

        try (Stream<Path> stream = Files.list(currentDir)) {
            stream.map(Path::getFileName)
                    .forEach(System.out::println);
        } catch (IOException e) {
            // Handle exception
            System.out.println(e.getMessage());
        }
    }
}
