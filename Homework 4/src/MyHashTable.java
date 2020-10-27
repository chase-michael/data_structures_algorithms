/**
 * 17683 Data Structures for Application Programmers.
 * <p>
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 * <p>
 * Andrew ID:
 *
 * @author
 */
public class MyHashTable implements MyHTInterface {

    /**
     * The default size for the internal array.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The default load factor which determines how many elements can be present in the
     * internal array before rehashing.
     */
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    /**
     * Constant to indicate item in a cell (index) has been deleted.
     */
    private static final DataItem DELETED = new DataItem("");

    /**
     * The DataItem array of the table.
     */
    private DataItem[] hashArray;

    /**
     * The maximum number of elements allowed in the array before the array is rehashed.
     */
    private int threshold;

    /**
     * The number of collisions that occur in the Hash Table.
     */
    private int collisions;

    /**
     * The number of elements present in the Hash Table.
     */
    protected int size;


    /**
     * Constructor that initialises the Hash Table with the default capacity.
     */
    public MyHashTable() {
        int startingCapacity = PrimeNumberGenerator.getNextPrime(DEFAULT_CAPACITY);
        hashArray = new DataItem[startingCapacity];
        threshold = (int) (hashArray.length * DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructor that initialises the Hash Table with the given capacity.
     *
     * @param initialCapacity The initial capacity for the Hash Table
     */
    public MyHashTable(int initialCapacity) {
        if (initialCapacity <= 0) throw new RuntimeException();
        hashArray = new DataItem[initialCapacity];
        threshold = (int) (hashArray.length * DEFAULT_LOAD_FACTOR);
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     * <p>
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     * <p>
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     * <p>
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     * <p>
     * But, to make the hash process faster, Horner's method should be applied as follows;
     * <p>
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     * <p>
     * Note: You must use 27 for this homework.
     * <p>
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     *
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        if (input == null || input.equals("")) return -1;
        long hashIndex = 0;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isLetter(input.charAt(i))) return -1;
            int ascii = input.charAt(i);
            hashIndex = (hashIndex * 27) + (ascii - 96);
            //System.out.printf("character: %c, value: %d\n", input.charAt(i), ascii-96);
        }
        hashIndex = (hashIndex % hashArray.length);
        if (hashIndex < 0) {
            hashIndex += hashArray.length;
        }
        return (int) hashIndex;
    }

    /**
     * Doubles array length and rehash items whenever the load factor is reached.
     * Note: do not include the number of deleted spaces to check the load factor.
     * Remember that deleted spaces are available for insertion.
     */
    private void rehash() {
        DataItem[] oldArray = hashArray;
        int newSize = PrimeNumberGenerator.getNextPrime(oldArray.length * 2);
        hashArray = new DataItem[newSize];
        System.out.printf("Rehashing %d items, new length is %d\n", size, newSize);
        size = 0;
        collisions = 0;

        for (DataItem element : oldArray) {
            if (element == null || element.value.equals("")) continue;
            insert(element.value);
            hashArray[findIndex(element.value)].frequency = element.frequency;
        }
        threshold = (int) (hashArray.length * DEFAULT_LOAD_FACTOR);
    }

    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    @Override
    public void insert(String value) {
        /* hashFunc returns -1 if string is not valid. ensureCapacity resizes array if at threshold */
        if (value == null) return;
        int key = hashFunc(value);
        if (key == -1) return;

        /* If index is empty, add value. If index already contains string, increase frequency */
        if (hashArray[key] == null) {
            hashArray[key] = new DataItem(value);
            size++;

        } else if (hashArray[key].value.equals(value)) {
            hashArray[key].frequency++;

        /* Index contains deleted item or some other value */
        } else {

            /* Setting up iterator */
            int index = key + 1;
            while (index < hashArray.length + key) {
                int cursor = index % hashArray.length;

                /* End of cluster. If original index was a deleted item add it there, else append to cluster */
                if (hashArray[cursor] == null) {

                    if (hashArray[key].value.equals("")) {
                        hashArray[key].value = value;
                    }
                    else {
                        hashArray[cursor] = new DataItem(value);
                    }
                    collisions++;
                    size++;
                    ensureCapacity();
                    return;
                }

                /* Value found, increase frequency */
                if (hashArray[cursor].value.equals(value)) {
                    hashArray[cursor].frequency++;
                    ensureCapacity();
                    return;
                }
                index++;
            }
        }
        ensureCapacity();
    }

    private void ensureCapacity() {
        threshold = (int) (hashArray.length * DEFAULT_LOAD_FACTOR);
        if (size > threshold) rehash();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        for (int i = 0; i < hashArray.length; i++) {

            if (hashArray[i] == null) {
                System.out.print(" **");
            } else if (hashArray[i].value.equals("")) {
                System.out.print(" #DEL#");
            } else {
                System.out.print(" "+hashArray[i].toString());
            }
        }
        System.out.println("");
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.equals("")) return false;
        return findIndex(key) >= 0;
    }

    @Override
    public int numOfCollisions() {
        return collisions;
    }

    @Override
    public int hashValue(String value) {
        return hashFunc(value);
    }

    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     * NOTE: If best hash index for string is null, string does not exist in the hashArray
     * because any index that previously had an element will still contain an empty DataItem
     */
    @Override
    public int showFrequency(String key) {
        if (key == null || key.equals("")) return 0;
        return (findIndex(key) != -1) ? hashArray[findIndex(key)].frequency : 0;
    }

    /** @return Index of hashed string. If not found, return -1
     */
    private int findIndex(String key) {
        int index = hashFunc(key);
        int start = index;
        while (index < hashArray.length + start) {
            int cursor = index % hashArray.length;
            if (hashArray[cursor] == null) return -1;
            if (hashArray[cursor].value.equals(key)) return cursor;
            index++;
        }
        return -1;
    }

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    @Override
    public String remove(String key) {
        if (key == null || key.equals("")) return null;
        int hashIndex = findIndex(key);
        if (hashIndex == -1) return null;
        hashArray[hashIndex] = DELETED;
        size--;
        if (hashFunc(key) != hashIndex) collisions--;
        return key;
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        /**
         * Default constructor.
         *
         * @param str The value to store in the DataItem
         */
        DataItem(String str) {
            value = str;
            frequency = 1;
        }

        /**
         * Converts the object into a string using this form [value, frequency].
         *
         * @return the string version of the object
         */
        @Override
        public String toString() {
            return "[" + value + ", " + frequency + "]";
        }
    }

}
