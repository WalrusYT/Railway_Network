package dataStructures;

import java.io.Serializable;

/**
 * The interface Two way list.
 *
 * @param <E> the type parameter
 */
public interface TwoWayList<E extends Serializable> extends List<E> {
    /**
     * Two way iterator.
     *
     * @return the two way iterator
     */
    TwoWayIterator<E> twoWayIterator();
}
