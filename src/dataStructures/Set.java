package dataStructures;

import java.io.Serializable;

/**
 * The interface Set.
 *
 * @param <T> the type parameter
 */
public interface Set<T> extends Serializable {
    /**
     * Adds an element to the set
     *
     * @param element the element that should be added to the Set
     * @return <code>false</code> if this element was previously already added
     * <code>true</code> otherwise.
     */
    boolean add(T element);

    /**
     * Removes an element from the set
     *
     * @param element the element that should be removed from the Set
     * @return <code>true</code> if removable is successive and
     * <code>false</code> otherwise.
     */
    boolean remove(T element);

    /**
     * Checks if the elements contains in the set
     *
     * @param element the element that should be found in the set
     * @return <code>true</code> if it's in the set and <code>false</code> otherwise.
     */
    boolean contains(T element);

    /**
     * Returns the size of the set.
     *
     * @return the size of the set
     */
    int size();

    /**
     * Checks if the set is empty
     *
     * @return <code>true</code> if the set is empty and <code>false</code> otherwise.
     */
    boolean isEmpty();

    /**
     * Returns an iterator of the set
     *
     * @return the iterator of the set
     */
    Iterator<T> iterator();
}
