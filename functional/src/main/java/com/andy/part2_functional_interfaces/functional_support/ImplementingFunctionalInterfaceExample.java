package com.andy.part2_functional_interfaces.functional_support;

import java.util.function.Supplier;

public class ImplementingFunctionalInterfaceExample {

    // Custom functional interface that extends Supplier<String>
    interface TextEditorCommand extends Supplier<String> {
        String execute();

        // Default method from Supplier<String> interface
        default String get() {
            return execute();
        }
    }

    // Concrete implementation of TextEditorCommand
    static class OpenFileCommand implements TextEditorCommand {
        private final String filePath;

        public OpenFileCommand(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String execute() {
            // Simulate opening the file and returning its content
            return "Content of file: " + filePath;
        }
    }

    // Higher-order function that takes a TextEditorCommand and executes it
    static void executeCommand(TextEditorCommand command) {
        String result = command.get();
        System.out.println("Command executed: " + result);
    }

    public static void main(String[] args) {
        // Create an instance of OpenFileCommand
        TextEditorCommand openFileCommand = new OpenFileCommand("document.txt");

        // Execute the command using the higher-order function
        executeCommand(openFileCommand);
    }

/*
    Comments explaining when to use explicitly implemented functional interfaces:
     1. Use when you have existing classes or interfaces representing commands or actions that can be naturally expressed as functions.
     2. Useful for bridging the gap between object-oriented and functional programming paradigms.
     3. Enables compatibility with higher-order functions that expect functional interfaces, even for classes not originally designed as functional.
     4. Extending functional interfaces allows adding default methods to connect existing methods without altering implementations.
     5. Maintains backward compatibility and facilitates gradual introduction of functional concepts without breaking existing code.
*/
}
