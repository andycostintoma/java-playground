package org.andy.data_structures.graphs;

import java.util.Arrays;

public class GraphAsAdjacencyMatrix {

    private static class Graph {

        private int[][] adjacencyMatrix;
        private int numVertices;

        private Graph(int numVertices) {
            this.numVertices = numVertices;
            this.adjacencyMatrix = new int[numVertices][numVertices];
            for (int i = 0; i < numVertices; i++) {
                Arrays.fill(adjacencyMatrix[i], 0);
            }
        }

        // Add a new edge between vertices u and v: O(1) time complexity
        private void addEdge(int u, int v) {
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            adjacencyMatrix[u][v] = 1;
            adjacencyMatrix[v][u] = 1; // For undirected graph (symmetric)
        }

        // Remove an edge between vertices u and v: O(1) time complexity
        private void removeEdge(int u, int v) {
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            adjacencyMatrix[u][v] = 0;
            adjacencyMatrix[v][u] = 0; // For undirected graph (symmetric)
        }

        // Check whether there is an edge between vertices u and v: O(1) time complexity
        private boolean isAdjacent(int u, int v) {
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            return adjacencyMatrix[u][v] == 1;
        }

        // List all the neighbors of a vertex: O(n) time complexity
        private void printNeighbors(int vertex) {
            if (vertex < 0 || vertex >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            System.out.print("Neighbors of vertex " + vertex + ": ");
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[vertex][i] == 1) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

        // Add a new vertex: O(n^2) time complexity
        private void addVertex() {
            numVertices++;
            int[][] newMatrix = new int[numVertices][numVertices];
            for (int i = 0; i < numVertices - 1; i++) {
                System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, numVertices - 1);
            }
            adjacencyMatrix = newMatrix;
        }

        // Remove an existing vertex: O(n^2) time complexity
        private void removeVertex(int vertex) {
            if (vertex < 0 || vertex >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            numVertices--;
            int[][] newMatrix = new int[numVertices][numVertices];
            for (int i = 0, newRow = 0; i < numVertices + 1; i++) {
                if (i == vertex) {
                    continue;
                }
                for (int j = 0, newCol = 0; j < numVertices + 1; j++) {
                    if (j == vertex) {
                        continue;
                    }
                    newMatrix[newRow][newCol] = adjacencyMatrix[i][j];
                    newCol++;
                }
                newRow++;
            }
            adjacencyMatrix = newMatrix;
        }

        // Get the number of vertices: O(1) time complexity
        private int getNumVertices() {
            return numVertices;
        }

        // Print the adjacency matrix
        private void printAdjacencyMatrix() {
            System.out.println("Adjacency Matrix...");
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        // Add edges between vertices
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        // Print the adjacency matrix after adding edges
        System.out.println("Adding edges between vertices...");
        graph.printAdjacencyMatrix();

        // Remove an edge between vertices
        graph.removeEdge(1, 2);

        // Print the adjacency matrix after removing an edge
        System.out.println("Removing an edge between vertices...");
        graph.printAdjacencyMatrix();

        // Add a new vertex and edges
        graph.addVertex();
        graph.addEdge(0, 4);
        graph.addEdge(4, 2);

        // Print the adjacency matrix after adding a new vertex and edges
        System.out.println("Adding a new vertex and edges...");
        graph.printAdjacencyMatrix();

        // Remove an existing vertex
        graph.removeVertex(2);

        // Print the adjacency matrix after removing an existing vertex
        System.out.println("Removing an existing vertex...");
        graph.printAdjacencyMatrix();

        // Check whether two vertices are adjacent
        System.out.println("Is vertex 0 adjacent to vertex 3? " + graph.isAdjacent(0, 3));

        // List neighbors of a vertex
        graph.printNeighbors(0);
    }
}
