package com.andy.part4_streams.parallel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Illustrates parallel data processing with Streams in Java.
 * This program counts the occurrences of distinct words in a text file using parallel Streams.
 * <p>
 * <strong>Concurrency versus Parallelism:</strong>
 * Concurrency is about dealing with multiple tasks at once, while parallelism is about executing multiple tasks at once.
 * This program demonstrates parallelism by processing data concurrently.
 * </p>
 * <p>
 * <strong>Streams as Parallel Functional Pipelines:</strong>
 * Java Streams provide an easy-to-use data processing pipeline with parallel processing capabilities.
 * The use of the parallel() method switches the Stream into parallel mode, enabling parallel processing.
 * </p>
 * <p>
 * <strong>Choosing the Right Data Source:</strong>
 * This program uses Files.lines() to read lines from a text file, which is a suitable data source for parallel processing.
 * </p>
 * <p>
 * <strong>Number of Elements:</strong>
 * The text file contains a large number of lines, enabling efficient parallel processing with multiple threads.
 * </p>
 * <p>
 * <strong>Stream Operations:</strong>
 * Stream operations, such as mapping, filtering, and collecting, are applied to process the data efficiently in parallel.
 * </p>
 * <p>
 * <strong>Available Resources:</strong>
 * The program utilizes the available CPU cores and memory to achieve parallelism.
 * It demonstrates Amdahl's law, which governs the theoretical limits of parallel execution.
 * </p>
 */

public class ReadingLineByLineParallel {

    public static void main(String[] args) {
        // Path to the text file
        var location = Path.of("./war-and-peace.txt");

        // Precompiled regular expression patterns for text cleaning
        var punctuation = Pattern.compile("\\p{Punct}");
        var whitespace = Pattern.compile("\\s+");
        var words = Pattern.compile("\\w+");

        try (Stream<String> stream = Files.lines(location)) {
            // Using the lines method to get a Stream<String> of file lines

            // Count word occurrences in parallel
            Map<String, Integer> wordCount =
                    stream.parallel()
                            .map(punctuation::matcher)
                            .map(matcher -> matcher.replaceAll(""))
                            .map(whitespace::split)
                            .flatMap(Arrays::stream)
                            .filter(word -> words.matcher(word).matches())
                            .map(String::toLowerCase)
                            .collect(Collectors.toMap(
                                    Function.identity(),
                                    word -> 1,
                                    Integer::sum
                            ));

            System.out.println("Word Count: " + wordCount);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
