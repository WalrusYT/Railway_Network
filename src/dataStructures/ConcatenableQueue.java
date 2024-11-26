package dataStructures;

import java.io.Serializable;

/**
 * ConcatableQueue
 * Appends all elements from the given {@code ConcatenableQueue} to the end of this queue.
 * @param <E> type of the elements stored in the queue
 */
public interface ConcatenableQueue<E extends Serializable> extends Queue<E> {
    void append(ConcatenableQueue<E> queue);
}
