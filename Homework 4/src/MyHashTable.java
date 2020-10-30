/**
 * 17683 Data Structures for Application Programmers.
 * <p>
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 * <p>
 * Andrew ID: kbhuwalk
 *
 * @author Kunal Bhuwalka
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
    private int size;


    /**
     * Constructor that initialises the Hash Table with the default capacity.
     */
    public MyHashTable() {
        
    }

    /**
     * Constructor that initialises the Hash Table with the given capacity.
     *
     * @param initialCapacity The initial capacity for the Hash Table
     */
    public MyHashTable(int initialCapacity) {

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
        return 0;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     * Note: do not include the number of deleted spaces to check the load factor.
     * Remember that deleted spaces are available for insertion.
     */
    private void rehash() {

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
