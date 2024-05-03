package Projects;

public class Recursive{
    static int sequence(int n) {
        // Base cases
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n == 2)
            return 2;

        // Recursive case
        return sequence(n - 1) + 2 * sequence(n - 3);
    }

    public static void main(String[] args) {
        // Test the sequence function with different values of n
        for (int i = 0; i <= 10; i++) {
            System.out.println("sequence(" + i + ") = " + sequence(i));
        }
    }
}



