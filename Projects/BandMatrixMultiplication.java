package Projects;

import java.util.ArrayList;
import java.util.Random;

public class BandMatrixMultiplication {
    public static ArrayList<Double> bmv_brute_force(ArrayList<Double> A, int n, int m, ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            y.add(0.0);
            for (int j = Math.max(0, i - m); j <= Math.min(n - 1, i + m); j++) {
                int index = i * (2 * m + 1) + j - (i - m);
                y.set(i, y.get(i) + A.get(index) * x.get(j));
            }
        }
        return y;
    }

    public static void main(String[] args) {
        int n = 5000;  // Number of rows in the matrix
        int m = 5;  // Half of the band width

        // Generate a random matrix A
        double[][] A = generateRandomMatrix(n, m);

        // Define the vector x
        ArrayList<Double> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add((double) i);  // Example: Sequential values
        }

        // Perform band matrix-vector multiplication
        ArrayList<Double> y_brute_force = bmv_brute_force(convertToArray(A), n, m, x);

        // Print the band matrix A
        System.out.println("Band Matrix A:");
        for (double[] row : A) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        // Print the vector x
        System.out.println("\nVector x:");
        for (double element : x) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Print the resulting vector y
        System.out.println("\nResulting Vector y:");
        for (double element : y_brute_force) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Helper method to generate a random matrix
    private static double[][] generateRandomMatrix(int n, int m) {
        double[][] matrix = new double[n][2 * m + 1];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * m + 1; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    // Helper method to convert 2D array to ArrayList
    private static ArrayList<Double> convertToArray(double[][] A) {
        ArrayList<Double> list = new ArrayList<>();
        for (double[] row : A) {
            for (double element : row) {
                list.add(element);
            }
        }
        return list;
    }
}
