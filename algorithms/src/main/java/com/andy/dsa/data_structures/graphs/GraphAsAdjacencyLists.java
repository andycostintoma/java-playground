package com.andy.dsa.data_structures.graphs;

import java.util.ArrayList;
import java.util.List;

public class GraphAsAdjacencyLists {

    private static class Graph {

        private final List<List<Integer>> adjacencyLists;
        private int numVertices;

        private Graph(int numVertices) {
            this.numVertices = numVertices;
            this.adjacencyLists = new ArrayList<>(numVertices);
            for (int i = 0; i < numVertices; i++) {
                adjacencyLists.add(new ArrayList<>());
            }
        }

        // Add a new edge between vertices u and v: O(1) time complexity
        private void addEdge(int u, int v) {
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            adjacencyLists.get(u).add(v);
            adjacencyLists.get(v).add(u); // For undirected graph (symmetric)
        }

        // Remove an edge between vertices u and v: O(n) time complexity
        private void removeEdge(int u, int v) {
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            adjacencyLists.get(u).remove(Integer.valueOf(v));
            adjacencyLists.get(v).remove(Integer.valueOf(u)); // For undirected graph (symmetric)
        }

        // Check whether there is an edge between vertices u and v: O(n) time complexity
        private boolean isAdjacent(int u, int v) {
            if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            return adjacencyLists.get(u).contains(v);
        }

        // List all the neighbors of a vertex: O(1) time complexity
        private List<Integer> getNeighbors(int vertex) {
            if (vertex < 0 || vertex >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            return adjacencyLists.get(vertex);
        }

        // Add a new vertex: O(1) time complexity
        private void addVertex() {
            numVertices++;
            adjacencyLists.add(new ArrayList<>());
        }

        // Remove an existing vertex: O(n) time complexity
        private void removeVertex(int vertex) {
            if (vertex < 0 || vertex >= numVertices) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            adjacencyLists.remove(vertex);
            numVertices--;
            for (List<Integer> list : adjacencyLists) {
                list.remove(Integer.valueOf(vertex));
            }
        }

        // Get the number of vertices: O(1) time complexity
        private int getNumVertices() {
            return numVertices;
        }

        // Print the adjacency lists
        private void printAdjacencyLists() {
            System.out.println("Adjacency Lists...");
            for (int i = 0; i < numVertices; i++) {
                System.out.print(i + " -> ");
                for (Integer neighbor : adjacencyLists.get(i)) {
                    System.out.print(neighbor + " ");
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

        // Print the adjacency lists after adding edges
        System.out.println("Adding edges between vertices...");
        graph.printAdjacencyLists();

        // Remove an edge between vertices
        graph.removeEdge(1, 2);

        // Print the adjacency lists after removing an edge
        System.out.println("Removing an edge between vertices...");
        graph.printAdjacencyLists();

        // Add a new vertex and edges
        graph.addVertex();
        graph.addEdge(0, 4);
        graph.addEdge(4, 2);

        // Print the adjacency lists after adding a new vertex and edges
        System.out.println("Adding a new vertex and edges...");
        graph.printAdjacencyLists();

        // Remove an existing vertex
        graph.removeVertex(2);

        // Print the adjacency lists after removing an existing vertex
        System.out.println("Removing an existing vertex...");
        graph.printAdjacencyLists();

        // Check whether two vertices are adjacent
        System.out.println("Is vertex 0 adjacent to vertex 3? " + graph.isAdjacent(0, 3));

        // List neighbors of a vertex
        System.out.println("Neighbors of vertex 0: " + graph.getNeighbors(0));
    }
}
