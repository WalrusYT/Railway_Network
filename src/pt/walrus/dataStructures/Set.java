package pt.walrus.dataStructures;

import java.io.Serializable;

/**
 * The interface Set.
 *
 * @param <T> the type parameter
 */
public interface Set<T> extends Serializable {
    /**
     * Add boolean.
     *
     * @param element the element
     * @return the boolean
     */
    boolean add(T element);

    /**
     * Remove boolean.
     *
     * @param element the element
     * @return the boolean
     */
    boolean remove(T element);

    /**
     * Contains boolean.
     *
     * @param element the element
     * @return the boolean
     */
    boolean contains(T element);

    /**
     * Size int.
     *
     * @return the int
     */
    int size();

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    boolean isEmpty();

    /**
     * Iterator iterator.
     *
     * @return the iterator
     */
    Iterator<T> iterator();
}
