import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.rmi.NoSuchObjectException;
import java.util.*;

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
        if (input == null) throw new NullPointerException();
        if (input.equals("")) return;
        insertWordsToMap(input.split("\\W+"));
    }

    /**
     * A constructor to initialize the HashMap with the whole text of given file.
     *
     * @param file A file to read for word frequencies from
     */
    public Similarity(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
            numOfLines++;
        }
        for (String line : lines) {
            insertWordsToMap(line.split("\\W+"));
        }
    }

    private void insertWordsToMap(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (isAValidWord(word)) {
                if (hashMap.containsKey(word)) {
                    hashMap.replace(word, hashMap.get(word).add(BigInteger.ONE));
                } else {
                    hashMap.put(word, BigInteger.ONE);
                }
            }
        }
    }

    private boolean isAValidWord(String word) {
        if (word.equals("")) return false;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }

    /**
     * Calculates the number of lines in the file passed to the constructor.
     *
     * @return The number of lines present in the file.
     */
    public int numOfLines() {
        return numOfLines;
    }

    /**
     * Number of words present in the internal HashMap. This uses the frequencies of
     * each word, and thus, takes into account duplicate words.
     *
     * @return Total number of words in the HashMap
     */
    public BigInteger numOfWords() {
        BigInteger wordCount = BigInteger.ZERO;
        for (BigInteger frequency : hashMap.values()) {
            wordCount = wordCount.add(frequency);
        }
        return wordCount;
    }

    /**
     * This is the number of unique words in the dataset.
     *
     * @return Return number of distinct words in the dataset
     */
    public int numOfWordsNoDups() {
        int wordCount = 0;
        for (String ignored : hashMap.keySet()) {
            wordCount++;
        }
        return wordCount;
    }

    /**
     * Calculates the euclidean norm or the length of the word vector.
     *
     * @return The euclidean norm of the internal HashMap.
     */
    public double euclideanNorm() {
        double eN = 0;
        for (BigInteger frequency : hashMap.values()) {
            eN += Math.pow(frequency.doubleValue(), 2);
        }
        return Math.sqrt(eN);
    }

    public double euclideanNorm(Map<String, BigInteger> map) {
        if (map == null) throw new NullPointerException();
        double eN = 0;
        for (BigInteger frequency : map.values()) {
            eN += Math.pow(frequency.doubleValue(), 2);
        }
        return Math.sqrt(eN);
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
        if (map == null) throw new NullPointerException();
        if (map.isEmpty() || hashMap.isEmpty()) return 0;
        double dotProduct = 0;
        for (String word : map.keySet()) {
            if (hashMap.get(word) != null) {
                dotProduct += hashMap.get(word).doubleValue() * map.get(word).doubleValue();
            }
        }
        return dotProduct;
    }

    /**
     * Calculates the cosine distance between this vector and the given vector.
     * Uses the following formula: (dot product/ product of the euclidean distances)
     *
     * @param map The vector to compare to
     * @return the scalar distance value
     */
    public double distance(Map<String, BigInteger> map) {
        if (map == null) throw new NullPointerException();
        double euclideanProduct = euclideanNorm() * euclideanNorm(map);
        return Math.acos(dotProduct(map) / euclideanProduct);
    }

    /**
     * Creates a copy of the internal HashMap to return.
     *
     * @return A clone of the HashMap
     */
    public Map<String, BigInteger> getMap() {
        return new HashMap<>(hashMap);
    }

}
