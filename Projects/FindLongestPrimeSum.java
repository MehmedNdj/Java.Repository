package Projects;

public class FindLongestPrimeSum {

    public static void main(String[] args) {
        int n = 100000; // Limit for finding prime numbers
        int targetPrime = -1; // Initialize to -1 to indicate not found
        int longestSequenceLength = 0;

        System.out.println("Finding prime that can be written as the sum of the longest consecutive prime sequence...");

        // Find all prime numbers less than or equal to n
        boolean[] prime = sieveOfEratosthenes(n);

        // Iterate through all prime numbers
        for (int i = 2; i <= n; i++) {
            if (!prime[i]) {
                continue; // Skip non-prime numbers
            }

            int sequenceSum = 0; // Initialize sequenceSum to 0
            int sequenceLength = 0; // Initialize sequenceLength to 0

            // Iterate backwards from the current prime
            for (int j = i; j >= 2 && sequenceSum < i; j--) {
                if (prime[j]) {
                    sequenceSum += j; // Add j (the current prime number) to sequenceSum
                    sequenceLength++;
                }

                // Check if the current sum is a prime and update if necessary
                if (sequenceSum == i && sequenceLength > longestSequenceLength) {
                    targetPrime = i;
                    longestSequenceLength = sequenceLength;
                }
            }
        }

        // Print the result
        if (targetPrime != -1) {
            System.out.println("Prime: " + targetPrime + " (Sum of " + longestSequenceLength + " consecutive primes)");
        } else {
            System.out.println("No prime found within the limit of " + n);
        }
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        return prime;
    }
}
