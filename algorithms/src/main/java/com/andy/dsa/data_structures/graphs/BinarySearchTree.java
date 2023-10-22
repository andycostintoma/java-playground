package com.andy.dsa.data_structures.graphs;

public class BinarySearchTree {

    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Find operation
    // Time Complexity: O(h), where h is the height of the tree (log N on average for balanced trees, N in the worst case for skewed trees)
    public boolean find(int key) {
        return find(root, key);
    }

    private boolean find(Node node, int key) {
        if (node == null) {
            return false; // Key not found
        }

        if (node.key == key) {
            return true; // Key found
        }

        if (key < node.key) {
            return find(node.left, key); // Search in the left subtree
        } else {
            return find(node.right, key); // Search in the right subtree
        }
    }

    // Insert operation
    // Time Complexity: O(h), where h is the height of the tree (log N on average for balanced trees, N in the worst case for skewed trees)
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key); // Insert in the left subtree
        } else if (key > node.key) {
            node.right = insert(node.right, key); // Insert in the right subtree
        }

        return node;
    }

    // Remove operation
    // Time Complexity: O(h), where h is the height of the tree (log N on average for balanced trees, N in the worst case for skewed trees)
    public void remove(int key) {
        root = remove(root, key);
    }

    private Node remove(Node node, int key) {
        if (node == null) {
            return node; // Key not found
        }

        if (key < node.key) {
            node.left = remove(node.left, key); // Remove in the left subtree
        } else if (key > node.key) {
            node.right = remove(node.right, key); // Remove in the right subtree
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children; get the inorder successor (smallest in the right subtree)
            node.key = minValue(node.right);

            // Delete the inorder successor
            node.right = remove(node.right, node.key);
        }

        return node;
    }

    private int minValue(Node node) {
        int minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }

    // Print the tree in a tree-like format
    // Time Complexity: O(N), where N is the number of nodes in the tree
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int depth) {
        if (node == null) {
            return;
        }

        // Print the right subtree with increased depth
        printTree(node.right, depth + 1);

        // Print the current node with appropriate indentation
        for (int i = 0; i < depth; i++) {
            System.out.print("    "); // Adjust the number of spaces for indentation
        }
        System.out.println(node.key);

        // Print the left subtree with increased depth
        printTree(node.left, depth + 1);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Binary Search Tree:");
        tree.printTree();

        System.out.println("\nSearch for key 30: " + tree.find(30));
        System.out.println("Search for key 90: " + tree.find(90));

        System.out.println("\nRemove key 30:");
        tree.remove(30);
        tree.printTree();
    }
}
