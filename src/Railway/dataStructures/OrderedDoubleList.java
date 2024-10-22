package Railway.dataStructures;

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

    private DoubleListNode<Entry<K,V>> findNode (K key) {
        // it compares keys until it finds
        // - an equal key
        // - a bigger key, or
        // it gets to the end of the list (returns null in this case)
        DoubleListNode<Entry<K,V>> node = head;
        while ( node != null && node.getElement().getKey().compareTo(key) < 0)
        {
            node = node.getNext();
        }
        return node;
    }

    // it calls findNode and returns the value of the found node
    @Override
    public V find(K key) {
        return findNode(key).getElement().getValue();
    }

    // it calls findNode and it inserts the new node before the found node,
    // or at the end of the list findNote returns null.
    // If the keys are the same, it updates the value
    @Override
    public V insert(K key, V value) {
        DoubleListNode<Entry<K,V>> node = findNode(key);
        // if ( node != null and key are the same )... else () ..
        if (node == null) {
            if (isEmpty()) {
                head = node;
                // new node
            } else {
                tail.setNext(node);
                // new node
            }
            tail = node;
            currentSize++;
        } else {
            DoubleListNode<Entry<K,V>> prevNode = node.getPrevious();
            DoubleListNode<Entry<K,V>> nextNode = node.getNext();
            Entry<K,V> element;
            DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(, prevNode, nextNode);
            node.setNext(nextNode);
            node.setPrevious(prevNode);
            prevNode.setNext(node);
            nextNode.setPrevious(node);
        }
    }

    // it calls findNode. If the key is the same of the found node, it removes it
    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }
}
