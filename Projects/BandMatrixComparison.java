package Projects;

import java.util.ArrayList;
import java.util.Random;

public class BandMatrixComparison {
    public static void main(String[] args) {
        int n = 5000;  // Number of rows in the matrix
        int m = 5;     // Half of the band width

        // Measure time for brute-force version
        long startTimeBruteForce = System.currentTimeMillis();
        runBruteForceVersion(n, m);
        long endTimeBruteForce = System.currentTimeMillis();
        long executionTimeBruteForce = endTimeBruteForce - startTimeBruteForce;

        // Measure time for optimized version
        long startTimeOptimized = System.currentTimeMillis();
        runOptimizedVersion(n, m);
        long endTimeOptimized = System.currentTimeMillis();
        long executionTimeOptimized = endTimeOptimized - startTimeOptimized;

        // Print time usage for both versions
        System.out.println("Time usage for Brute-Force Version: " + executionTimeBruteForce + " milliseconds");
        System.out.println("Time usage for Optimized Version: " + executionTimeOptimized + " milliseconds");
    }

    // Method to run the brute-force version
    private static void runBruteForceVersion(int n, int m) {
        ArrayList<Double> A = new ArrayList<>();
        for (int i = 0; i < n * (2 * m + 1); i++) {
            A.add(i * 0.1);
        }

        ArrayList<Double> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add((double) i);
        }

        BandMatrixMultiplication.bmv_brute_force(A, n, m, x);
    }

    // Method to run the optimized version
    private static void runOptimizedVersion(int n, int m) {
        double[] A = new double[n * (2 * m + 1)];
        for (int i = 0; i < A.length; i++) {
            A[i] = i * 0.1;
        }

        ArrayList<Double> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add((double) i);
        }

        BandMatrixMultiplicationOptimized.bmv_optimized(A, n, m, x);
    }
}
