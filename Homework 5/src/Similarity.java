import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The similarities between documents are to be determined by the degree of the
 * overlapping in contents of two documents. There are many different and complex algorithms
 * to answer this question. In this homework, you are going to use an algorithm, called cosine similarity.
 * <p>
 * Name:
 *
 * @author
 */
public class Similarity {

    /**
     * The internal data structure to store the word count frequencies.
     */
    private Map<String, BigInteger> hashMap = new HashMap<>();

    /**
     * The value of Pi/2. This is returned when there are no similarities between the two vectors.
     */
    private static final double HALF_PI = Math.PI / 2;

    /**
     * Number of lines in the File passed in the constructor.
     */
    private int numOfLines;

    /**
     * Regular Expression used to match valid words.
     */
    private static final String WORD_PATTERN = "^[a-zA-Z]+$";

    /**
     * A constructor to initialize the HashMap with the given string input.
     *
     * @param input A string of text to initialize the HashMap with.
     */
    public Similarity(String input) {

    }

    /**
     * A constructor to initialize the HashMap with the whole text of given file.
     *
     * @param file A file to read for word frequencies from
     */
    public Similarity(File file) {

    }

    /**
     * Calculates the number of lines in the file passed to the constructor.
     *
     * @return The number of lines present in the file.
     */
    public int numOfLines() {
        return 0;
    }

    /**
     * Number of words present in the internal HashMap. This uses the frequecies of
     * each word, and thus, takes into account duplicate words.
     *
     * @return Total number of words in the HashMap
     */
    public BigInteger numOfWords() {
        return 0;
    }

    /**
     * This is the number of unique words in the dataset.
     *
     * @return Return number of distinct words in the dataset
     */
    public int numOfWordsNoDups() {
        return 0;
    }

    /**
     * Calculates the euclidean norm or the length of the word vector.
     *
     * @return The euclidean norm of the internal HashMap.
     */
    public double euclideanNorm() {
        return 0;
    }

    /**
     * Calculates the dot product of this vector with the passed in Map.
     * This is not quadratic because it goes through the keyset once O(N),
     * and the get method for the passed in Map is O(1).
     * As a result, this method runs in O(N) time complexity.
     *
     * @param map The second map to use for calculation
     * @return The dot product scalar value
     */
    public double dotProduct(Map<String, BigInteger> map) {
        return 0;
    }

    /**
     * Calculates the cosine distance between this vector and the given vector.
     * Uses the following formula: (dot product/ product of the euclidean distances)
     *
     * @param map The vector to compare to
     * @return the scalar distance value
     */
    public double distance(Map<String, BigInteger> map) {
        return 0;
    }

    /**
     * Creates a copy of the internal HashMap to return.
     *
     * @return A clone of the HashMap
     */
    public Map<String, BigInteger> getMap() {
        return null;
    }

}
