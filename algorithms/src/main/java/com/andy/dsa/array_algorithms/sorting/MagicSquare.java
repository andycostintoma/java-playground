package com.andy.dsa.array_algorithms.sorting;

public class MagicSquare {
    
    public static void generateMagicSquare(int n) {
        if (n % 2 == 0) {
            System.out.println("Magic square can only be generated for odd order.");
            return;
        }

        int[][] magicSquare = new int[n][n];

        int row = 0;
        int col = n / 2;

        for (int num = 1; num <= n * n; num++) {
            magicSquare[row][col] = num;
            row--;
            col++;

            if (num % n == 0) {
                row += 2;
                col--;
            } else if (col == n) {
                col -= n;
            } else if (row < 0) {
                row += n;
            }
        }

        // Print the magic square
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magicSquare[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int order = 3; // Change this to the desired odd order
        generateMagicSquare(order);
    }
}
