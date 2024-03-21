package Projects;
import java.util.ArrayList;
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

    // Example usage (replace with your actual data)
    public static void main(String[] args) {
        int n = 5;
        int m = 1;
        ArrayList<Double> A = new ArrayList<>();
        for (int i = 0; i < n * (2 * m + 1); i++) {
            A.add(i * 0.1);
        }

        ArrayList<Double> x = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                x.add(i * 1.0);
        }

        ArrayList<Double> y_brute_force = bmv_brute_force(A, n, m, x);

        System.out.println("Band Matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * (2 * m + 1) + j - (i - m);
                if (j >= i - m && j <= i + m) {
                    System.out.print(A.get(index) + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }

        // Print the vector x
        System.out.println("Vector x:");
        for (double element : x) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Print the resulting vector y
        System.out.println("Resulting Vector y:");
        for (double element : y_brute_force) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

