import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberGeneratorTest {

    @Test
    void getNextPrime() {
        int[] inputs = new int[] {22, 128, 44, 97, 3, 2};
        boolean[] isPrimeExpected = new boolean[] {false, false, false, true, true, true};
        int[] getNextPrimeExpected = new int[] {23, 131, 47, 97, 3, 2};
        System.out.println("=== getNextPrime Test ===");
        for (int i = 0; i < inputs.length; i++) {
            int nextPrime = PrimeNumberGenerator.getNextPrime(inputs[i]);
            System.out.println("-\nTest case: " + inputs[i] +
                    "\nisPrime Expected: " + isPrimeExpected[i]+", Actual: " + PrimeNumberGenerator.isPrime(inputs[i]) +
                    "\ngetNextPrime Expected: " + getNextPrimeExpected[i] + ", Actual: " + PrimeNumberGenerator.getNextPrime(inputs[i]));
        }
        System.out.println("==========");
    }
}
