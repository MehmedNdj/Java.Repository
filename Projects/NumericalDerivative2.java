package Projects;

public class NumericalDerivative2 {
    public static void main(String[] args) {
        double x = Math.PI; // Value of x
        double h = 1e-16; // Value of h

        // Calculate the numerical derivative using central differencing
        double numericalDerivative = calculateDerivative(x, h);

        System.out.println("Numerical derivative at x = π: " + numericalDerivative);
    }

    // Function to calculate the derivative using central differencing
    public static double calculateDerivative(double x, double h) {
        double fx_plus_h = Math.sin(x + h); // f(x + h)
        double fx_minus_h = Math.sin(x - h); // f(x - h)

        // Numerical approximation of the derivative using central differencing
        double derivative = (fx_plus_h - fx_minus_h) / (2 * h);

        return derivative;
    }
}