import org.junit.platform.commons.annotation.Testable;

public class PrimeNumberGenerator {

    /** Gives next prime number following input. */
    public static int getNextPrime(int input) {
        while (!isPrime(input)) { input++; }
        return input;

    }

    protected static boolean isPrime(int input) {
        if (input <= 3) return input > 1;
        if (input % 2 == 0 || input % 3 == 0) return false;
        int i = 5;
        while (i * i <= input) {
            if (input % i == 0 || input % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }

}
