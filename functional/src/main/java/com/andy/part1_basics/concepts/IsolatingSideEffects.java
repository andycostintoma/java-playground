package com.andy.part1_basics.concepts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>It's impossible to write applications with absolutely zero side effects. In object-oriented programming (OOP) or imperative code, mutable states and side effects are often present. However, to maintain code reasonability and reduce bugs, side effects affecting state should be isolated and preferably placed on the edges of logical units rather than scattered throughout the code.</p>
 *
 * <p>The Unix philosophy, which emphasizes writing programs that do one thing well and work together, can be applied to functional programming as well. Functions should aim to do one thing only and do it well without affecting their environment. Complex tasks are better served by composing multiple pure functions, preserving purity as long as possible.</p>
 *
 * <p>I/O operations, such as loading files or interacting with databases, often involve side effects. To encapsulate a side effect, separate the operation into loading the data and processing it as discrete steps. This isolates the side effect in one function, while data processing remains a pure and reusable function.</p>
 *
 * <p>Here's an example illustrating the concept of isolating side effects:</p>
 */

public class IsolatingSideEffects {

    /**
     * <p>Loads the content of a file at the specified path.</p>
     *
     * @param filePath The path of the file to load.
     * @return The content of the file as an Optional string.
     */
    public static Optional<String> loadFile(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            return Optional.of(lines.collect(Collectors.joining(System.lineSeparator())));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * <p>Processes the content of a file.</p>
     *
     * @param fileContent The content of the file.
     * @return The processed result as a string.
     */
    public static String processFileContent(String fileContent) {
        return "Processed: " + fileContent;
    }

    public static void main(String[] args) {
        Path filePath = Path.of("war-and-peace.txt");

        // Load file content (side effect isolated in loadFile)
        Optional<String> fileContent = loadFile(filePath);

        // Process file content (pure function)
        String result = fileContent.map(IsolatingSideEffects::processFileContent)
                                  .orElse("File not found or empty.");

        System.out.println(result);
    }
}
