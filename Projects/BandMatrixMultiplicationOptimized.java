package Projects;

import java.util.ArrayList;
import java.util.Random;

public class BandMatrixMultiplicationOptimized {
    public static ArrayList<Double> bmv_optimized(double[] A, int n, int m, ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>(n);
        int bandWidth = 2 * m + 1;

        // Iterate through each row of the matrix
        for (int i = 0; i < n; i++) {
            double sum = 0.0;

            // Calculate starting and ending indices for each row
            int start = Math.max(0, i - m);
            int end = Math.min(n - 1, i + m);

            // Iterate through the band elements and multiply with corresponding x values
            for (int j = start; j <= end; j++) {
                sum += A[i * bandWidth + (j - i + m)] * x.get(j);
            }

            // Store the result in the resulting vector y
            y.add(sum);
        }
        return y;
    }

    public static void main(String[] args) {
        int n = 5000;  // Matrix dimension
        int m = 5;  // Bandwidth
        int bandWidth = 2 * m + 1; // Declare bandWidth here

        // Generate A as a small array of (2m-1) x n elements
        double[] A = generateRandomMatrix(n, bandWidth);

        // Generate vector x
        ArrayList<Double> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add((double) i);  // Example: Sequential values
        }

        // Print the band matrix A
        System.out.println("Band Matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < bandWidth; j++) {
                System.out.print(A[i * bandWidth + j] + " ");
            }
            System.out.println();
        }

        // Print the vector x
        System.out.println("\nVector x:");
        for (double element : x) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Perform optimized multiplication
        ArrayList<Double> y_optimized = bmv_optimized(A, n, m, x);

        // Print the resulting vector y
        System.out.println("\nResulting Vector y:");
        for (double element : y_optimized) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Helper method to generate a random matrix
    private static double[] generateRandomMatrix(int n, int bandWidth) {
        double[] matrix = new double[n * bandWidth];
        Random random = new Random();
        for (int i = 0; i < n * bandWidth; i++) {
            matrix[i] = random.nextDouble();
        }
        return matrix;
    }
}
