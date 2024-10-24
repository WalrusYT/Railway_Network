package dataStructures;

public class InvertibleQueueClass<E> extends QueueInList<E> implements InvertibleQueue<E>  {
    private boolean isInverted;

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
