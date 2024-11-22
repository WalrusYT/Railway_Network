package pt.walrus.dataStructures;

public class SepChainHashTableIterator<K extends Comparable<K>, V> implements Iterator<Entry<K,V>> {
    private final Dictionary<K, V>[] elements;
    private Iterator<Entry<K, V>> iteratorOfBucket;
    private int bucketCounter;

    public SepChainHashTableIterator(Dictionary<K,V>[] elements) {
        this.elements = elements;
        rewind();
    }

    private void setNextBucket() {
        while (elements[++bucketCounter].isEmpty()) {
            if (bucketCounter >= elements.length - 1) {
                this.iteratorOfBucket = null;
                return;
            }
        }
        this.iteratorOfBucket = elements[bucketCounter].iterator();
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
