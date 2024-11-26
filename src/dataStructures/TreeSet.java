package dataStructures;

/**
 * The TreeSet class
 *
 * @param <T> the type parameter
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> {
    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 0L;
    /**
     * Set memory
     */
    private final OrderedDictionary<T, T> dict;

    public TreeSet() {
        this.dict = new AVLTree<>();
    }

    @Override
    public boolean add(T element) {
        return dict.insert(element, element) == null;
    }

    @Override
    public boolean remove(T element) {
        return dict.remove(element) != null;
    }

    @Override
    public boolean contains(T element) {
        return dict.find(element) != null;
    }

    @Override
    public int size() {
        return dict.size();
    }

    @Override
    public boolean isEmpty() {
        return dict.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<Entry<T, T>> dictIterator = dict.iterator();

            @Override
            public boolean hasNext() {
                return dictIterator.hasNext();
            }

            @Override
            public T next() throws NoSuchElementException {
                return dictIterator.next().getKey();
            }

            @Override
            public void rewind() {
                dictIterator.rewind();
            }
        };
    }
}
