package Projects;

import java.util.ArrayList;
import java.util.Random;

public class BandMatrixMultiplicationOptimized {

    public static ArrayList<Double> bmv_optimized(double[] A, int n, int m, ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            int k = Math.max(0, i - m); // Starting index in A for this row

            System.out.println("Row " + i + ":"); // Track row being processed

            // Iterate only up to (i + m), ensuring k stays within bounds
            for (int j = i - m; j <= Math.min(i + m, n - 1); j++, k++) {
                // Check if the index is still within bounds (redundant but safe)
                if (k >= 0 && k < (2 * m + 1) * n) {
                    System.out.println("  - Accessing A[" + k + "] * x[" + j + "]"); // Print accessed elements
                    sum += A[k] * x.get(j);
                }
            }
            y.add(sum);
        }
        return y;
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 1;

        // Generate A as a small array of (2m+1) x n elements
        double[] A = new double[(2 * m + 1) * n];
        Random random = new Random();
        for (int i = 0; i < (2 * m + 1) * n; i++) {
            A[i] = random.nextDouble(); // Replace with your desired random value generation
        }

        // Generate vector x
        ArrayList<Double> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add(random.nextDouble()); // Replace with your desired random value generation
        }

        // Convert double[] A to ArrayList<Double> for brute-force compatibility
        ArrayList<Double> A_list = new ArrayList<>();
        for (double value : A) {
            A_list.add(value);
        }

        // Perform both brute-force and optimized multiplication
        ArrayList<Double> y_brute_force = bmv_brute_force(A_list, n, m, x);
        ArrayList<Double> y_optimized = bmv_optimized(A, n, m, x);

        // Verify results
        boolean resultsMatch = true;
        for (int i = 0; i < n; i++) {
            if (Math.abs(y_brute_force.get(i) - y_optimized.get(i)) > 1e-10) { // Allow for small numerical differences
                resultsMatch = false;
                break;
            }
        }

        System.out.println("Results match: " + resultsMatch);
    }

    // Original brute-force version for comparison (using ArrayList for compatibility)
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
}
