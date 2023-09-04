package org.andy.data_structures.graphs;

public class BinaryTreeExample {

    static class Node {
        int key;
        String value;
        Node left;
        Node right;

        public Node(int key, String value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static class BinaryTree {
        Node root;


        public BinaryTree(Node root) {
            this.root = root;
        }

        // Access: O(1)
        public Node getRoot() {
            return root;
        }

        // Search by key and return value: O(n) - in the worst case, where n is the number of nodes in the tree
        public String searchByKey(int key) {
            return searchByKey(root, key);
        }

        private String searchByKey(Node node, int key) {
            if (node == null) {
                return null; // Key not found
            }

            if (node.key == key) {
                return node.value; // Key found, return the associated value
            }

            String leftResult = searchByKey(node.left, key);
            if (leftResult != null) {
                return leftResult; // Key found in the left subtree, return the associated value
            }

            return searchByKey(node.right, key); // Key found in the right subtree, return the associated value (or null if not found)
        }

        // Insertion: O(1) - when inserting as the left or right child of a node with a known reference
        public void insertLeft(Node parent, Node newNode) {
            if (parent != null) {
                parent.left = newNode;
            }
        }

        public void insertRight(Node parent, Node newNode) {
            if (parent != null) {
                parent.right = newNode;
            }
        }

        // Deletion: O(1) - when deleting a known leaf node
        public void delete(Node parent, Node nodeToDelete) {
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = null;
                } else if (parent.right == nodeToDelete) {
                    parent.right = null;
                }
            }
        }

        // Print the tree in a tree-like format
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
                System.out.print("         "); // Adjust the number of spaces for indentation
            }
            System.out.println(node.key + ": " + node.value);

            // Print the left subtree with increased depth
            printTree(node.left, depth + 1);
        }
    }

    public static void main(String[] args) {
        // Create nodes
        Node node2 = new Node(2, "B", null, null);
        Node node3 = new Node(3, "C", null, null);
        Node root = new Node(1, "A", node2, node3);

        // Create a binary tree with the root node
        BinaryTree binaryTree = new BinaryTree(root);

        // Insert nodes as children
        Node node4 = new Node(4, "D", null, null);
        binaryTree.insertLeft(node2, node4);

        // Print the tree
        binaryTree.printTree();

        // Search for a value by key
        int keyToSearch = 2;
        String result = binaryTree.searchByKey(keyToSearch);
        if (result != null) {
            System.out.println("Value found for key " + keyToSearch + ": " + result);
        } else {
            System.out.println("Value not found for key " + keyToSearch);
        }

        // Delete a node (e.g., node 2)
        binaryTree.delete(root, node2);

        // Print the tree after deletion
        System.out.println("\nTree after deletion:");
        binaryTree.printTree();
    }

}
