package Projects;

public class NumericalDerivative {
    public static void main(String[] args) {
        double x = Math.PI; // Value of x
        double h = 1e-16; // Value of h

        // Calculate the numerical derivative
        double numericalDerivative = calculateDerivative(x, h);

        // Analytically obtained value of the derivative at x = π
        double analyticalDerivative = Math.cos(x);

        System.out.println("Numerical derivative at x = π: " + numericalDerivative);
        System.out.println("Analytical derivative at x = π: " + analyticalDerivative);
        System.out.println("Difference: " + Math.abs(numericalDerivative - analyticalDerivative));
    }

    // Function to calculate the derivative using the definition of the derivative
    public static double calculateDerivative(double x, double h) {
        double fx = Math.sin(x); // f(x)
        double fx_plus_h = Math.sin(x + h); // f(x + h)

        // Numerical approximation of the derivative using the definition of the derivative
        double derivative = (fx_plus_h - fx) / h;

        return derivative;
    }
}
