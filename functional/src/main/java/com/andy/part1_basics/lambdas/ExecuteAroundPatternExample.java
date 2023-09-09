package com.andy.part1_basics.lambdas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>Demonstrates the Execute-Around Pattern using lambdas and functional interfaces.</p>
 *
 * <p>The Execute-Around Pattern is a common pattern for resource processing, such as dealing with files or databases.
 * It involves opening a resource, performing some processing, and then closing the resource. The setup and cleanup phases
 * are repetitive and similar, while the important code for processing the resource is in the middle.</p>
 */
public class ExecuteAroundPatternExample {


    public static final String FILE_NAME = "war-and-peace.txt";

    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    static String processFile(BufferedReaderProcessor p, String fileName) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(fileName))) {
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFile(BufferedReader::readLine, FILE_NAME);
        System.out.println("Result of reading one line: " + oneLine);

    String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine(), FILE_NAME);
        System.out.println("Result of reading two lines: " + twoLines);
    }
}
