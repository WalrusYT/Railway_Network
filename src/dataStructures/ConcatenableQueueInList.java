package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * A queue implementation that supports concatenation of another queue's elements.
 * This class extends {@link QueueInList} and implements the {@link ConcatenableQueue} interface.
 * It allows appending the elements of another {@code ConcatenableQueue}
 * to the end of the current queue.
 *
 * @param <E> the type of elements held in this queue
 */
public class ConcatenableQueueInList<E extends Serializable> extends QueueInList<E>
        implements ConcatenableQueue<E> {
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    /**
     * Constructs an empty {@code ConcatenableQueueInList}.
     */
    public ConcatenableQueueInList() {
        super();
    }
    /**
     * Appends all elements from the given {@code ConcatenableQueue} to the end of this queue.
     *
     * @param queue the {@code ConcatenableQueue} whose elements will be added to this queue
     * @throws ClassCastException if the internal list cannot be cast to {@code DoubleList<E>}
     */
    public void append(ConcatenableQueue<E> queue) {
        if (queue instanceof ConcatenableQueueInList<E>) {
            DoubleList<E> l1 = (DoubleList<E>) this.list;
            DoubleList<E> l2 = (DoubleList<E>) ((ConcatenableQueueInList<E>) queue).list;
            l1.append(l2);
        }
        else {
            while(!queue.isEmpty())
                this.enqueue(queue.dequeue());
        }
    }
}
