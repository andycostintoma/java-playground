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

            // Process each line:
            // 1. Remove punctuation
            // 2. Split line into words
            // 3. Flatten the array of words into a Stream of words
            // 4. Filter out non-word elements
            // 5. Convert words to lowercase
            // 6. Collect into a Map with word as key and word count as value

            System.out.println("Word Count: " + wordCount);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
