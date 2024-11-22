package pt.walrus.dataStructures;

/**
 * An interface representing a queue with the additional capability to invert its order.
 * This interface extends {@link Queue}, adding a method to reverse the order of elements.
 *
 * @param <E> the type of elements held in this queue
 */
public interface InvertibleQueue<E> extends Queue<E> {
    /**
     * Reverses the order of all elements in the queue.
     * After calling this method, the first element becomes the last,
     * and the last element becomes the first.
     */
    void invert();
}
