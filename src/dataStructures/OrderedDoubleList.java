package dataStructures;

public class OrderedDoubleList <K extends Comparable<K>, V> implements Dictionary<K,V>{
    protected DoubleListNode<Entry<K, V>> head;
    protected DoubleListNode<Entry<K,V>> tail;

    protected int currentSize;

    public OrderedDoubleList () {
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

    // finds a node, where the key is greater than or equals to the specified one
    private DoubleListNode<Entry<K,V>> findNextOrExisting(K key) {
        // it compares keys until it finds
        // - an equal key
        // - a bigger key, or
        // it gets to the end of the list (returns null in this case)
        DoubleListNode<Entry<K,V>> node = head;
        while (node != null && node.getElement().getKey().compareTo(key) < 0) {
            node = node.getNext();
        }
        return node;
    }

    // locates existing node by utilizing findNextOrExisting method and checking whether the keys are equal
    private DoubleListNode<Entry<K,V>> findExisting(K key) {
        DoubleListNode<Entry<K,V>> node = findNextOrExisting(key);
        return node != null && node.getElement().getKey().equals(key) ? node : null;
    }

    // uses findExisting method to locate the node and returns its value if successful, otherwise null
    @Override
    public V find(K key) {
        DoubleListNode<Entry<K,V>> node = findExisting(key);
        return node == null ? null : node.getElement().getValue();
    }

    // inserts a new key-value pair into the dict, preserving its order
    // defined by the key's compareTo method and by utilizing findNextOrExisting method
    @Override
    public V insert(K key, V value) {
        currentSize++;
        Entry<K,V> entry = new EntryClass<>(key, value);
        // if dict is empty - set head and tail as new node
        if (tail == null) {
            head = new DoubleListNode<>(entry);
            tail = head;
            return null;
        }
        DoubleListNode<Entry<K,V>> next = findNextOrExisting(key);
        // if new key is greater than any key present - append newNode to end of dict
        if (next == null) {
            DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(entry, tail, null);
            tail.setNext(newNode);
            tail = newNode;
            return null;
        }
        // if new key already exists - change the value and return the old one
        if (next.getElement().getKey().equals(key)) {
            V oldValue = next.getElement().getValue();
            next.setElement(entry);
            currentSize--; // no element was inserted - decrement size
            return oldValue;
        }
        // otherwise insert new node before the node with a greater key
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(entry, next.getPrevious(), next);
        // if next node is the first one in dict - modify head
        if (next == head) {
            head = newNode;
            next.setPrevious(newNode);
            return null;
        }
        next.getPrevious().setNext(newNode);
        next.setPrevious(newNode);
        return null;
    }

    // removes an existing key-value pair by utilizing findExisting method
    @Override
    public V remove(K key) {
        DoubleListNode<Entry<K,V>> node = findExisting(key);
        // if node doesn't exist - return null
        if (node == null) return null;
        V value = node.getElement().getValue();
        currentSize--;
        // if found node is the only node in dict (tail == head -> 1 entry in dict) - set head and tail to null
        if (tail == head) {
            head = null;
            tail = null;
            return value;
        }
        // if node is the first element in dict - modify head
        if (node == head) {
            head = node.getNext();
            node.getNext().setPrevious(null);
            return value;
        }
        // if node is the last element in dict - modify tail
        if (node == tail) {
            tail = node.getPrevious();
            node.getPrevious().setNext(null);
            return value;
        }
        // otherwise modify references of the removed nodes neighbours
        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
        return value;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoubleListIterator<>(head, tail);
    }
}
