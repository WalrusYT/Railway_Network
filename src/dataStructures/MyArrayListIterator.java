package dataStructures;

public class MyArrayListIterator<E> implements Iterator<E> {
    private final E[] elements;
    private final int size;
    private int counter;

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
