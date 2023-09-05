package com.andy.part1_basics.concepts.recursion;

import java.util.Stack;
import java.util.function.Consumer;

public class TreeTraversal {

    record Node<T>(T value, Node<T> left, Node<T> right) {

        public static <T> Node<T> of(T value, Node<T> left, Node<T> right) {
            return new Node<>(value, left, right);
        }

        public static <T> Node<T> of(T value) {
            return new Node<>(value, null, null);
        }

        public static <T> Node<T> left(T value, Node<T> left) {
            return new Node<>(value, left, null);
        }

        public static <T> Node<T> right(T value, Node<T> right) {
            return new Node<>(value, null, right);
        }

        public static void traverseIterative(Node<String> root) {
            var tmpNodes = new Stack<Node<String>>();
            var current = root;

            while (!tmpNodes.isEmpty() || current != null) {
                if (current != null) {
                    tmpNodes.push(current);
                    current = current.left();
                    continue;
                }
                current = tmpNodes.pop();
                System.out.print(current.value());
                current = current.right();

            }
        }
        private static <T> void traverseRecursive(Node<T> node, Consumer<T> fn) {
            if (node == null) {
                return;
            }

            traverseRecursive(node.left(), fn);

            fn.accept(node.value());

            traverseRecursive(node.right(), fn);
        }

    }


    public static void main(String[] args) {
        var root = Node.of("1",
                Node.of("2",
                        Node.of("4",
                                Node.of("7"),
                                Node.of("8")),
                        Node.of("5")),
                Node.right("3",
                        Node.left("6",
                                Node.of("9"))));

        Node.traverseIterative(root);
        System.out.println();
        Node.traverseRecursive(root, System.out::printf);
    }
}

