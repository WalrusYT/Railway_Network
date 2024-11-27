package pt.walrus.dataStructures;

/**
 * An iterator for the {@code MyArrayList} class that iterates over elements in sequence.
 *
 * @param <E> the type of elements returned by this iterator
 */
public class MyArrayListIterator<E> implements Iterator<E> {
    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 0L;
    /**
     * Array of the elements of the list
     */
    private final E[] elements;
    /**
     * Size of the list
     */
    private final int size;
    /**
     * Counter that shows which is current position of iteration
     */
    private int counter;
    /**
     * Constructs an iterator for the specified array and size.
     *
     * @param elements the array of elements to iterate over
     * @param size the number of elements to iterate within the array
     */
    public MyArrayListIterator(E[] elements, int size) {
        this.elements = elements;
        this.size = size;
        this.counter = 0;
    }

    @Override
    public boolean hasNext() {
        return counter < size;
    }

    @Override
    public E next() throws NoSuchElementException {
        if ( !hasNext() )
            throw new NoSuchElementException();
        return elements[counter++];
    }

    @Override
    public void rewind() {
        counter = 0;
    }
}
