package dataStructures;
/**
 * A queue implementation that can invert the order in which elements are added and removed.
 * This class extends {@link QueueInList} and implements the {@link InvertibleQueue} interface.
 * When inverted, new elements are added to the front, and elements are removed from the back.
 *
 * @param <E> the type of elements held in this queue
 */
public class InvertibleQueueClass<E> extends QueueInList<E> implements InvertibleQueue<E>  {
    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 0L;
    private boolean isInverted;
    /**
     * Constructs an empty {@code InvertibleQueueClass}.
     * The queue is initially not inverted.
     */
    public InvertibleQueueClass () {
        super();
        isInverted = false;
    }
    @Override
    public void invert() {
        isInverted = !isInverted;
    }

    @Override
    public void enqueue(E element) {
        if (isInverted) {
            list.addFirst(element);
        }
        else {
            list.addLast(element);
        }
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if ( list.isEmpty() )
            throw new EmptyQueueException();
        if (!isInverted) {
            return list.removeFirst();
        } else {
            return list.removeLast();
        }
    }
}
