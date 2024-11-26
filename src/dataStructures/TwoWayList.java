package dataStructures;

/**
 * The interface Two way list.
 *
 * @param <E> the type parameter
 */
public interface TwoWayList<E> extends List<E> {
    /**
     * Two way iterator.
     *
     * @return the two way iterator
     */
    TwoWayIterator<E> twoWayIterator();
}
