package com.andy.part5_optionals_exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ExceptionHandlingWithOptional {

    public static void main(String[] args) {
        Path path1 = Path.of("Path1");
        Path path2 = Path.of("Path2");
        Path path3 = Path.of("Path3");

        Optional<String> result1 = safeReadString(path1);
        Optional<String> result2 = safeReadString(path2);
        Optional<String> result3 = safeReadString(path3);

        result1.ifPresent(System.out::println);
        result2.ifPresent(System.out::println);
        result3.ifPresent(System.out::println);
    }

    private static Optional<String> safeReadString(Path path) {
        try {
            var content = Files.readString(path);
            return Optional.of(content);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
