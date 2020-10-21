/**
 * Implements a Linked List data structure that is always sorted and uses only recursion.
 * Andrew Id:
 *
 * @author
 */
public class SortedLinkedList implements MyListInterface {

    private class Node {
        String data;
        Node next;

        private Node() {
            data = null;
            next = null;
        }
    }

    private Node head;

    public SortedLinkedList(String[] array) {
        if (array != null && array.length > 0)
            constructorHelper(array, 0);
    }

    private void constructorHelper(String[] array, int currentIndex) {
            this.add(array[currentIndex++]);
            if (currentIndex < array.length)
                constructorHelper(array, currentIndex);
        }

    /**
     * Inserts a new String.
     * Do not throw exceptions if invalid word is added (Gently ignore it).
     * No duplicates allowed and maintain the order in ascending order.
     * @param value String to be added.
     */
    @Override
    public void add(String value) {
        if (value == null || value.equals("")) return;

        if (head == null) {
            head = new Node();
            head.data = value;
            return;
        }

        addHelper(head, value, null);
    }

    private void addHelper(Node node, String toAdd, Node previous) {
        // Quietly ignore duplicates
        if (node.data.equals(toAdd)) return;

        // Goes before element being evaluated
        if (node.data.compareTo(toAdd) > 0) {
            Node newNode = new Node();
            newNode.data = toAdd;
            newNode.next = node;

            // If new first element reassign head, otherwise connect previous node
            if (head == node) {
                head = newNode;
            } else {
                previous.next = newNode;
            }
            return;
        }

        // Goes after current node; if node is last, append
        if (node.next == null) {
            Node newNode = new Node();
            newNode.data = toAdd;
            node.next = newNode;
        } else {
            previous = node;
            addHelper(node.next, toAdd, previous);
        }
    }

    /**
     * Checks the size (number of data items) of the list.
     * @return the size of the list
     */
    @Override
    public int size() {
        if (head == null) return 0;
        return sizeHelper(head, 0);
    }

    private int sizeHelper(Node temp, int count) {
        count++;
        if (temp.next == null) return count;
        return sizeHelper(temp.next, count);
    }

    /**
     * Displays the values of the list.
     */
    @Override
    public void display() {
        if (head == null) System.out.println("[]");
        displayHelper(head);
    }

    private void displayHelper(Node temp) {
        if (temp == head) System.out.print("[");
        System.out.print(temp.data);
        if (temp.next != null) {
            System.out.print(", ");
            displayHelper(temp.next);
        } else {
            System.out.println("]");
        }
    }

    /**
     * Returns true if the key value is in the list.
     * @param key String key to search
     * @return true if found, false if not found
     */
    @Override
    public boolean contains(String key) {
        if (head == null || key == null) return false;
        return containsHelper(head.next, key);
    }

    private boolean containsHelper(Node temp, String key) {
        if (temp.data.equals(key)) return true;
        if (temp.next == null) return false;
        return containsHelper(temp.next, key);
    }

    /**
     * Returns true is the list is empty.
     * @return true if it is empty, false if it is not empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes and returns the first String object of the list.
     * @return String object that is removed. If the list is empty, returns null
     */
    @Override
    public String removeFirst() {
        if (head == null) return null;

        String data = head.data;
        if (head.next == null) {
            head = null;
        } else {
            head = head.next;
        }

        return data;
    }

    /**
     * Removes and returns String object at the specified index.
     * @param index index to remove String object
     * @return String object that is removed
     * @throws RuntimeException for invalid index value (index < 0 || index >= size())
     */
    @Override
    public String removeAt(int index) {
        if (head == null || index < 0) throw new RuntimeException("Invalid index value.");
        if (index == 0) {
            String toReturn = head.data;
            removeFirst();
            return toReturn;
        }
        return removeAtHelper(1, index, head, null);
    }

    private String removeAtHelper(int currentIndex, int index, Node temp, Node previous) {
        if (currentIndex == index) {
            previous.next = temp.next;
            return temp.data;
        }

        previous = temp;

        if (temp.next == null) throw new RuntimeException("Invalid index value.");

        return removeAtHelper(currentIndex+1, index, temp.next, previous);
    }
}
