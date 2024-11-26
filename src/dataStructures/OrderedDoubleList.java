package dataStructures;

import java.io.Serializable;

/**
 * An ordered dictionary implemented as a doubly linked list, which stores key-value pairs
 * in sorted order based on a specified comparator.
 *
 * @param <K> the type of keys maintained by this dictionary
 * @param <V> the type of values associated with keys
 */
public class OrderedDoubleList<K extends Serializable, V extends Serializable>
        implements Dictionary<K, V>, Serializable {
    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 0L;
    /**
     *  Node at the head of the list.
     */
    protected DoubleListNode<Entry<K, V>> head;
    /**
     * Node at the tail of the list.
     */
    protected DoubleListNode<Entry<K, V>> tail;
    /**
     * Comparator to set the rules of comparing keys
     */
    protected Comparator<K> comparator;
    /**
     * Constructs an ordered dictionary with a specified comparator to define the ordering.
     *
     * @param comparator the comparator used to determine the order of keys
     */
    protected int currentSize;
    /**
     * Constructs an ordered double list with a specified comparator to define the ordering.
     *
     * @param comparator the comparator used to determine the order of keys
     */
    public OrderedDoubleList(Comparator<K> comparator) {
        this.comparator = comparator;
        head = null;
        tail = null;
        currentSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Finds the first node with a key greater than or equal to the specified key.
     * @param key the key to compare against
     * @return the node with a key greater than or equal to the specified key,
     * or {@code null} if no such node exists
     */
    private DoubleListNode<Entry<K,V>> findNextOrExisting(K key) {
        DoubleListNode<Entry<K,V>> node = head;
        while (node != null && comparator.compare(node.getElement().getKey(), key) < 0) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Searches for a node with the specified key in the dictionary.
     * @param key the key to search for
     * @return the node with the matching key, or {@code null} if no such node exists
     */
    private DoubleListNode<Entry<K,V>> findExisting(K key) {
        DoubleListNode<Entry<K,V>> node = findNextOrExisting(key);
        return node != null && comparator.compare(node.getElement().getKey(), key) == 0 ? node : null;
    }

    @Override
    public V find(K key) {
        DoubleListNode<Entry<K,V>> node = findExisting(key);
        return node == null ? null : node.getElement().getValue();
    }

    @Override
    public V insert(K key, V value) {
        currentSize++;
        Entry<K,V> entry = new EntryClass<>(key, value);
        if (tail == null) {
            head = new DoubleListNode<>(entry);
            tail = head;
            return null;
        }
        DoubleListNode<Entry<K,V>> next = findNextOrExisting(key);
        if (next == null) {
            DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(entry, tail, null);
            tail.setNext(newNode);
            tail = newNode;
            return null;
        }
        if (next.getElement().getKey().equals(key)) {
            V oldValue = next.getElement().getValue();
            next.setElement(entry);
            currentSize--;
            return oldValue;
        }
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(entry, next.getPrevious(), next);
        if (next == head) {
            head = newNode;
            next.setPrevious(newNode);
            return null;
        }
        next.getPrevious().setNext(newNode);
        next.setPrevious(newNode);
        return null;
    }

    @Override
    public V remove(K key) {
        DoubleListNode<Entry<K,V>> node = findExisting(key);
        if (node == null) return null;
        V value = node.getElement().getValue();
        currentSize--;
        if (tail == head) {
            head = null;
            tail = null;
            return value;
        }
        if (node == head) {
            head = node.getNext();
            node.getNext().setPrevious(null);
            return value;
        }
        if (node == tail) {
            tail = node.getPrevious();
            node.getPrevious().setNext(null);
            return value;
        }
        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
        return value;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoubleListIterator<>(head, tail);
    }
}
