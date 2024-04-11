package Projects;

public class SquareRootCalculator {
    public static double sqrtWithPrecision(int n) {
        // Handle edge cases
        if (n < 0) {
            throw new IllegalArgumentException("Cannot compute square root of a negative number");
        }
        if (n == 0 || n == 1) {
            return n;
        }

        // Define precision as 0.01 for two decimal places
        double precision = 0.01;

        // Initialize left and right pointers for binary search
        double left = 0;
        double right = n;

        // Binary search to find the square root within the precision range
        while ((right - left) > precision) {
            double mid = (left + right) / 2;
            double square = mid * mid;

            // Check if mid is close enough to the square root of n
           if (square < n) {
               left = mid;
           }
           else {
               right = mid;
           }
        }

        return (left + right) / 2;
    }

    // Main method for testing
    public static void main(String[] args) {
        int n = 70; // Example input
        double squareRoot = sqrtWithPrecision(n);
        System.out.println("Square root of " + n + " is approximately: " + String.format("%.2f", squareRoot));
    }
}

