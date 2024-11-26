package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * A custom implementation of a dynamically resizing array-based list.
 * This class implements the {@link List} interface and supports common list operations,
 * including adding, removing, finding elements, and iterating over them.
 *
 * @param <E> the type of elements held in this list
 */
public class MyArrayList<E extends Serializable> implements List<E>, Serializable {
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    /**
     * Initial capacity of the array
     */
    private static final int INITIAL_CAPACITY = 8;
    /**
     * Size of the list
     */
    private int size;
    /**
     * Array of the elements of type E
     */
    private E[] elements;
    /**
     * Constructs an empty {@code MyArrayList} with an initial capacity.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        size = 0;
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<>(elements, size);
    }

    @Override
    public E getFirst() throws EmptyListException {
        if (this.isEmpty()) throw new EmptyListException();
        return elements[0];
    }

    @Override
    public E getLast() throws EmptyListException {
        if (this.isEmpty()) throw new EmptyListException();
        return elements[size - 1];
    }

    @Override
    public E get(int position) throws InvalidPositionException {
        if (position < 0 || position >= size) throw new InvalidPositionException();
        return elements[position];
    }

    @Override
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) return i;
        }
        return -1;
    }

    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    @Override
    public void addLast(E element) {
        if (size == elements.length) resize();
        elements[size++] = element;
    }

    @Override
    public void add(int position, E element) throws InvalidPositionException {
        if (position < 0 || position > size) throw new InvalidPositionException();
        if (size == elements.length) resize();
        System.arraycopy(elements, position, elements, position + 1, size - position);
        elements[position] = element;
        size++;
    }

    /**
     * Resizes the array, when it's full
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newElements = (E[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public E removeFirst() throws EmptyListException {
        if (isEmpty()) throw new EmptyListException();
        return remove(0);
    }

    @Override
    public E removeLast() throws EmptyListException {
        if (isEmpty()) throw new EmptyListException();
        E element = elements[--size];
        elements[size] = null;
        return element;
    }

    @Override
    public E remove(int position) throws InvalidPositionException {
        if (position < 0 || position >= size) throw new InvalidPositionException();
        if (position == size - 1) return removeLast();
        E element = elements[position];
        System.arraycopy(elements, position + 1, elements, position, size - position - 1);
        elements[--size] = null;
        return element;
    }

    @Override
    public boolean remove(E element) {
        int pos = find(element);
        if (pos == -1) return false;
        remove(pos);
        return true;
    }
}
