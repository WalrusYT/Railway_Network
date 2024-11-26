package dataStructures;

/**
 * Sep chain hash table iterator.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class SepChainHashTableIterator<K extends Comparable<K>, V> implements Iterator<Entry<K,V>> {
    private final Dictionary<K, V>[] elements;
    private Iterator<Entry<K, V>> iteratorOfBucket;
    private int bucketCounter;

    /**
     * Instantiates a new Sep chain hash table iterator.
     *
     * @param elements the elements
     */
    public SepChainHashTableIterator(Dictionary<K,V>[] elements) {
        this.elements = elements;
        rewind();
    }

    private void setNextBucket() {
        for (int i = bucketCounter + 1; i < elements.length; i++) {
            if (!elements[i].isEmpty()) {
                bucketCounter = i;
                iteratorOfBucket = elements[i].iterator();
                return;
            }
        }
        iteratorOfBucket = null;
    }
    @Override
    public boolean hasNext() {
        return iteratorOfBucket != null;
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException();
        Entry<K,V> entry = iteratorOfBucket.next();
        if (!iteratorOfBucket.hasNext()) {
            setNextBucket();
        }
        return entry;
    }

    @Override
    public void rewind() {
        this.bucketCounter = -1;
        setNextBucket();
    }
}
